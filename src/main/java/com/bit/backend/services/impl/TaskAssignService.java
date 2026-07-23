package com.bit.backend.services.impl;

import com.bit.backend.dtos.*;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.DefinedTasksMapper;
import com.bit.backend.mappers.TaskAssignMapper;
import com.bit.backend.repositories.*;
import com.bit.backend.services.CustomerServiceI;
import com.bit.backend.services.NotificationServiceI;
import com.bit.backend.services.TaskAssignServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TaskAssignService implements TaskAssignServiceI {
    private final TaskAssignRepository taskAssignRepository;
    private final TaskAssignMapper taskAssignMapper;
    private final DefinedTasksRepository definedTasksRepository;
    private final DefinedTasksMapper definedTasksMapper;
    private final SubTasksAssignRepository subTasksAssignRepository;
    private final UserRepository userRepository;
    private final CustomerServiceI customerServiceI;
    private final NotificationServiceI notificationServiceI;
    private final EmployeeRepository employeeRepository;
    private final AppointmentRepository appointmentRepository;
    private final PdfGenerateService pdfGenerateService;

    public TaskAssignService(TaskAssignRepository taskAssignRepository, TaskAssignMapper taskAssignMapper,
                             DefinedTasksRepository definedTasksRepository, DefinedTasksMapper definedTasksMapper,
                             SubTasksAssignRepository subTasksAssignRepository, UserRepository userRepository,
                             CustomerServiceI customerServiceI, NotificationServiceI notificationServiceI, EmployeeRepository employeeRepository, AppointmentRepository appointmentRepository, PdfGenerateService pdfGenerateService) {
        this.taskAssignRepository = taskAssignRepository;
        this.taskAssignMapper = taskAssignMapper;
        this.definedTasksRepository = definedTasksRepository;
        this.definedTasksMapper = definedTasksMapper;
        this.subTasksAssignRepository = subTasksAssignRepository;
        this.userRepository = userRepository;
        this.customerServiceI = customerServiceI;
        this.notificationServiceI = notificationServiceI;
        this.employeeRepository = employeeRepository;
        this.appointmentRepository = appointmentRepository;
        this.pdfGenerateService = pdfGenerateService;
    }

    @Override
    public List<DefinedTasksDto> getDefinedTasksData() {
        try {
            List<DefinedTasksEntity> definedTasksEntities = definedTasksRepository.findAll();
            List<DefinedTasksDto> definedTasksDtos = definedTasksMapper.toDefinedTasksDtos(definedTasksEntities);
            return definedTasksDtos;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // customer commonly used tasks loaded into the dashboard
    @Override
    public List<TaskAssignDto> getByCustomerId(Long customerId) {
        try {
            List<TaskAssignEntity> taskAssignEntityList = taskAssignRepository.findByCustomerId(customerId);
            return taskAssignMapper.toTaskAssignDtoList(taskAssignEntityList);
        } catch (Exception e) {
            throw new AppException("Request Failed with Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //supervisor's my tasks table
    @Override
    public List<SubTaskAssignDto> getAssignedSubTasksData(Long userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new AppException("User Not Found", HttpStatus.INTERNAL_SERVER_ERROR));
            if (user.getEmployee() == null) {
                throw new AppException("Invalid Employee. Please login with correct employee Id", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Long empId = user.getEmployee().getEmpNumber();

            List<SubTaskAssignedEntity> subTaskAssignedEntities = subTasksAssignRepository.findBySupervisor(empId);
//              List<SubTaskAssignedEntity> subTaskAssignedEntities = subTasksAssignRepository.findByAssignedUserId(empId);
            return taskAssignMapper.toSubTaskAssignDto(subTaskAssignedEntities);

        } catch (Exception e) {
            throw new AppException("Request Failed with Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public SubTaskStatusChangeDto subTaskStatusChange(SubTaskStatusChangeDto subTaskStatusChangeDto) {
//        try {
//            SubTaskAssignDto savedSubTasks = null;
//            Optional<SubTaskAssignedEntity> oSubTaskAssignedEntity = subTasksAssignRepository.findById(subTaskStatusChangeDto.getId());
//
//            if (oSubTaskAssignedEntity.isPresent()) {
//                SubTaskAssignedEntity subTaskAssignedEntity = oSubTaskAssignedEntity.get();
//                subTaskAssignedEntity.setStatus(subTaskStatusChangeDto.getStatus());
//                savedSubTasks = taskAssignMapper.toSubTaskAssignDto(subTasksAssignRepository.save(subTaskAssignedEntity));
//            }
//
//            return subTaskStatusChangeDto;
//        } catch (Exception error) {
//            throw new AppException("Request Failed with Error: " + error, HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        try{
            SubTaskAssignedEntity subTask = subTasksAssignRepository.findById(subTaskStatusChangeDto.getId())
                    .orElseThrow(() ->
                            new AppException("Sub Task Not Found",
                                    HttpStatus.NOT_FOUND));

            //Update current subtask
            subTask.setStatus(subTaskStatusChangeDto.getStatus());
            subTasksAssignRepository.save(subTask);

            //Get MAin Task Number
            String taskNo = subTask.getMainUniqueTaskNo();

            //Get All subtasks of that main task
            List<SubTaskAssignedEntity> subtasks = subTasksAssignRepository.findByMainUniqueTaskNo(taskNo);

            boolean allDone = subtasks.stream()
                    .allMatch(st -> "Done"
                            .equalsIgnoreCase(st.getStatus()));

            TaskAssignEntity mainTask = taskAssignRepository.findByUniqueTaskNo(taskNo)
                    .orElseThrow(()->
                            new AppException("Main Task Not Found",
                                    HttpStatus.NOT_FOUND));

            if (allDone){
                mainTask.setStatus("Done");
            }else {
                mainTask.setStatus("Start");
            }
            taskAssignRepository.save(mainTask);
            return subTaskStatusChangeDto;
        } catch (Exception error){
            throw new AppException("Request Failed with Error: " + error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<TaskAssignDto> getMainTaskDetails(String customerId, String taskNo) {
        if (customerId.equals("-1") && taskNo.equals("-1")) {
            throw new AppException("Invalid Request", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // get customerId from userId

        User user = userRepository.findById(Long.parseLong(customerId)).orElseThrow(() -> new AppException("User Not Found", HttpStatus.INTERNAL_SERVER_ERROR));

//        if (user.getId() != null) {
//            Long cusId = user.getCustomer().getCusId();
//            List<TaskAssignEntity> taskAssignEntityList = this.taskAssignRepository.findByCustomerId(cusId);
//            List<TaskAssignDto> taskAssignDtoList = taskAssignMapper.toTaskAssignDtoList(taskAssignEntityList);
//            return taskAssignDtoList;
//        }

        if ((!taskNo.equals("-1")) && (taskNo != null || !taskNo.equals("") || !taskNo.equals(null))) {
//            Optional<TaskAssignEntity> taskAssignEntityList = this.taskAssignRepository.findByUniqueTaskNo(taskNo);
            TaskAssignEntity entity = taskAssignRepository
                    .findByUniqueTaskNo(taskNo)
                    .orElseThrow(() ->
                            new AppException("Task Not Found",
                                    HttpStatus.NOT_FOUND));

            List<TaskAssignDto> taskAssignDtoList = taskAssignMapper.toTaskAssignDtoList(List.of(entity));
            return taskAssignDtoList;
        } else if (taskNo.equals("-1")) {
            Long cusId = Long.parseLong(customerId);
            List<TaskAssignEntity> taskAssignEntityList = this.taskAssignRepository.findByCustomerId(cusId);
            List<TaskAssignDto> taskAssignDtoList = taskAssignMapper.toTaskAssignDtoList(taskAssignEntityList);
            return taskAssignDtoList;
        }
        return null;
    }

    @Override
    public List<TaskAssignDto> getMainTaskDetailsByUid(String uid) {
        try {
//            Optional<TaskAssignEntity> taskAssignEntityList = taskAssignRepository.findByUniqueTaskNo(uid);
            TaskAssignEntity entity = taskAssignRepository
                    .findByUniqueTaskNo(uid)
                    .orElseThrow(() ->
                            new AppException("Task Not Found",
                                    HttpStatus.NOT_FOUND));
            List<TaskAssignDto> taskAssignDtoList = taskAssignMapper.toTaskAssignDtoList(List.of(entity));
            return taskAssignDtoList;
        } catch (Exception e) {
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get tasks that assign to the supervisor into the task tracker
    @Override
    public List<TaskAssignDto> getSupervisorTasks(String employeeId, String taskNo){
        if (employeeId == null || employeeId.equals("-1") || employeeId.isEmpty()){
            throw new AppException("Invalid Supervisor Id", HttpStatus.BAD_REQUEST);
        }
        Long supervisor;
        try {
            supervisor = Long.parseLong(employeeId);
        }catch (NumberFormatException e){
            throw new AppException("Invalid Employee Id format", HttpStatus.BAD_REQUEST);
        }
        List<TaskAssignEntity> taskAssignEntityList;

        if (taskNo != null && !taskNo.equals("-1") && !taskNo.isEmpty()){
            taskAssignEntityList = taskAssignRepository.findBySupervisorAndUniqueTaskNo(supervisor, taskNo);
        } else {
            taskAssignEntityList = taskAssignRepository.findBySupervisor(supervisor);
        }
        return taskAssignMapper.toTaskAssignDtoList(taskAssignEntityList);
    }

    @Override
    public List<TaskAssignDto> getSupervisorTasksByEmployeeId(String employeeId) {
        try {
//            Optional<TaskAssignEntity> taskAssignEntityList = taskAssignRepository.findByUniqueTaskNo(employeeId);
            TaskAssignEntity entity = taskAssignRepository
                    .findByUniqueTaskNo(employeeId)
                    .orElseThrow(() ->
                            new AppException("Task Not Found",
                                    HttpStatus.NOT_FOUND));
            List<TaskAssignDto> taskAssignDtoList = taskAssignMapper.toTaskAssignDtoList(List.of(entity));
            return taskAssignDtoList;
        } catch (Exception e) {
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get all tasks when log as a manager
    @Override
    public List<TaskAssignDto> getAllTasks() {
        try {
//            Optional<TaskAssignEntity> taskAssignEntityList = taskAssignRepository.findByUniqueTaskNo(employeeId);
            List<TaskAssignEntity> taskAssignEntityList = taskAssignRepository.findAll();

            List<TaskAssignDto> taskAssignDtoList = taskAssignMapper.toTaskAssignDtoList(taskAssignEntityList);
            return taskAssignDtoList;
        } catch (Exception e) {
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @Override
    public TaskAssignDto addTaskAssignEntity(TaskAssignDto taskAssignDto){
        try {
            //Convert DTO to ENTITY
            TaskAssignEntity taskAssignEntity = taskAssignMapper.toTaskAssignEntity(taskAssignDto);

            //Get customer email if not provided
            if ((taskAssignDto.getEmail() == null || taskAssignDto.getEmail().equals("")) && taskAssignDto.getCustomerId() != null) {
                CustomerDto customerDto = customerServiceI.getCustomerById(taskAssignDto.getCustomerId());
                if (customerDto.getEmail() != null) {
                    taskAssignEntity.setEmail(customerDto.getEmail());
                }
            }
            //Save main task first
            TaskAssignEntity savedTask = taskAssignRepository.save(taskAssignEntity);
//            TaskAssignDto savedDto = null;
//            TaskAssignEntity updatedTask = null;
//
            //update appointment status
            updateAppointmentStatus(taskAssignDto.getAppointmentUniqueNo());
            //Generate Task number
            String taskNo = generateTaskNumber(savedTask);
            Long superVisorId = taskAssignDto.getSupervisor();
            String customer = taskAssignDto.getCustomerName();

            if (taskNo != null) {
                savedTask.setUniqueTaskNo(taskNo);
                // Save task with task number
                savedTask = taskAssignRepository.save(savedTask);
//                savedDto = taskAssignMapper.toTaskAssignDto(updatedTask);
                // Get subtasks
                List<SubTaskAssignedEntity> subTaskAssignedEntityList = savedTask.getSubTasks();

                int count = 1;

                for (SubTaskAssignedEntity subTaskAssignedEntity: subTaskAssignedEntityList) {
                    String subTaskNo = generateSubTaskNumber(taskNo, subTaskAssignedEntity, count);
                    subTaskAssignedEntity.setUniqueSubTaskNo(subTaskNo);
                    subTaskAssignedEntity.setSupervisor(superVisorId);
                    subTaskAssignedEntity.setMainUniqueTaskNo(taskNo);
                    subTaskAssignedEntity.setCustomer(customer);
                    subTaskAssignedEntity.setStatus("pending");

                    // Set technician name
                    if(subTaskAssignedEntity.getAssignedUserId() != null) {
                        EmployeeEntity employee = employeeRepository
                                .findById(subTaskAssignedEntity.getAssignedUserId())
                                .orElse(null);
                        if (employee != null) {
                            subTaskAssignedEntity.setAssignUserName(employee.getFullName());
                        }
                    }
//                    System.out.println("Saving Name: " + subTaskAssignedEntity.getAssigneUserName());
                    count = count + 1;
                }
                // Save subtasks after updating values
                List<SubTaskAssignedEntity> savedSubTasks =
                        subTasksAssignRepository.saveAll(subTaskAssignedEntityList);
//                List<SubTaskAssignDto> subTaskAssignDtoList = taskAssignMapper.toSubTaskAssignDto(subTasksAssignRepository.saveAll(subTaskAssignedEntityList));
                // Convert final entity to DTO
                TaskAssignDto savedDto =
                        taskAssignMapper.toTaskAssignDto(savedTask);

                // Convert subtasks to DTO and attach
                List<SubTaskAssignDto> subTaskDtoList =
                        taskAssignMapper.toSubTaskAssignDto(savedSubTasks);

                savedDto.setSubTasks(subTaskDtoList);

                savedDto.setEmail(savedTask.getEmail());
                savedDto.setUniqueTaskNo(savedTask.getUniqueTaskNo());

                // Send notification
                this.notificationServiceI
                        .sendTaskTrackerNotification(savedDto);


                return savedDto;
            }
//            taskAssignDto.setEmail(taskAssignEntity.getEmail());
//            taskAssignDto.setUniqueTaskNo(taskAssignEntity.getUniqueTaskNo());
//            // send mail to customer [Todo]
//            this.notificationServiceI.sendTaskTrackerNotification(taskAssignDto);
//            // send notification to employee [todo]

            return null;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //update status in appointment table after appointment assign to the task
    private void updateAppointmentStatus(String appointmentUniqueNo) {
        AppointmentEntity appointment = appointmentRepository
                .findByAppointment_UniqueNo(appointmentUniqueNo)
                .orElseThrow(() -> new RuntimeException("Appointment not Found: " + appointmentUniqueNo));

            appointment.setStatus("ASSIGNED_TO_TASK");
            appointmentRepository.save(appointment);
    }

    public String generateTaskNumber(TaskAssignEntity taskAssignEntity) {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String taskIdPart = String.valueOf(taskAssignEntity.getId());
        String uniquePart = String.format("%03d", new Random().nextInt(1000)); // 000 - 999

        return datePart + taskIdPart + uniquePart;
    }

    public String generateSubTaskNumber(String mainTaskNo, SubTaskAssignedEntity subTaskAssignedEntity, int count) {
        return mainTaskNo + count;
    }

    @Override
    public List<TaskAssignDto> getData() {
        try {
            List<TaskAssignEntity> taskAssignEntityList = taskAssignRepository.findAll();
            List<TaskAssignDto> taskAssignDtoList = taskAssignMapper.toTaskAssignDtoList(taskAssignEntityList);
            return taskAssignDtoList;
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public TaskAssignDto getTaskById(Long taskId) {
        Optional<TaskAssignEntity> optionalTask = taskAssignRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            return taskAssignMapper.toTaskAssignDto(optionalTask.get());
        } else {
            throw new AppException("Task not found with id: " + taskId, HttpStatus.NOT_FOUND);
        }
    }


//    @Override
//    public TaskAssignDto updateData(long taskId, TaskAssignDto taskAssignDto) {
//        int count = 0;
//        try {
//            Optional<TaskAssignEntity> optionalTaskAssignEntity = taskAssignRepository.findById(taskId);
//
//            if(!optionalTaskAssignEntity.isPresent()){
//                throw new AppException("Task Assign Does Not Exist", HttpStatus.BAD_REQUEST);
//            }
//
//            count = optionalTaskAssignEntity.get().getSubTasks().size() + 1;
//            taskAssignDto.setUniqueTaskNo(optionalTaskAssignEntity.get().getUniqueTaskNo());
//            TaskAssignEntity newTaskAssignEntity = taskAssignMapper.toTaskAssignEntity(taskAssignDto);
//
//            newTaskAssignEntity.setId(taskId);
//            String customer = newTaskAssignEntity.getCustomerName();
//            newTaskAssignEntity.setUniqueTaskNo(taskAssignDto.getUniqueTaskNo());
//
//            String uniqueTaskNo = optionalTaskAssignEntity.get().getUniqueTaskNo();
//            if (uniqueTaskNo == null || uniqueTaskNo.equals("") || uniqueTaskNo.equals(null)) {
//                uniqueTaskNo = generateTaskNumber(optionalTaskAssignEntity.get());
//                newTaskAssignEntity.setUniqueTaskNo(uniqueTaskNo);
//                count = 0;
//            }
//
//            TaskAssignEntity taskAssignEntity = taskAssignRepository.save(newTaskAssignEntity);
//            TaskAssignDto responseTaskAssignDto = taskAssignMapper.toTaskAssignDto(taskAssignEntity);
//            Long superVisorId = taskAssignEntity.getSupervisor();
//
//            if (responseTaskAssignDto != null) {
//                List<SubTaskAssignedEntity> subTaskAssignedEntityList = taskAssignEntity.getSubTasks();
//
//                for (SubTaskAssignedEntity subTaskAssignedEntity: subTaskAssignedEntityList) {
//                    String subTaskNo = "";
//                    if (subTaskAssignedEntity.getUniqueSubTaskNo() == null || subTaskAssignedEntity.getUniqueSubTaskNo().equals("") || subTaskAssignedEntity.getUniqueSubTaskNo().equals(null)) {
//                        subTaskNo  = generateSubTaskNumber(uniqueTaskNo, subTaskAssignedEntity, count);
//                    }
//                    subTaskAssignedEntity.setUniqueSubTaskNo(subTaskNo);
//                    subTaskAssignedEntity.setSupervisor(superVisorId);
//                    subTaskAssignedEntity.setMainUniqueTaskNo(uniqueTaskNo);
//                    subTaskAssignedEntity.setCustomer(customer);
//                    count = count + 1;
//                }
//
//                List<SubTaskAssignDto> subTaskAssignDtoList = taskAssignMapper.toSubTaskAssignDto(subTasksAssignRepository.saveAll(subTaskAssignedEntityList));
//            }
//
//            return responseTaskAssignDto;
//        } catch (Exception e){
//            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @Override
    @Transactional
    public TaskAssignDto updateData(long taskId, TaskAssignDto taskAssignDto) {

        try {

            TaskAssignEntity existingTask =
                    taskAssignRepository.findById(taskId)
                            .orElseThrow(() ->
                                    new AppException(
                                            "Task Assign Does Not Exist",
                                            HttpStatus.BAD_REQUEST
                                    ));


            // keep existing task number
            String uniqueTaskNo = existingTask.getUniqueTaskNo();

            if(uniqueTaskNo == null || uniqueTaskNo.isEmpty()){
                uniqueTaskNo = generateTaskNumber(existingTask);
                existingTask.setUniqueTaskNo(uniqueTaskNo);
            }


            /*
             * Update main task fields only
             * Do NOT replace entity
             */
            existingTask.setAppointmentUniqueNo(
                    taskAssignDto.getAppointmentUniqueNo()
            );

            existingTask.setDate(taskAssignDto.getDate());
            existingTask.setTime(taskAssignDto.getTime());
            existingTask.setTaskName(taskAssignDto.getTaskName());
            existingTask.setServiceType(taskAssignDto.getServiceType());
            existingTask.setTaskCreatedBy(taskAssignDto.getTaskCreatedBy());
            existingTask.setCustomerName(taskAssignDto.getCustomerName());
            existingTask.setLicencePlate(taskAssignDto.getLicencePlate());
            existingTask.setVehicleType(taskAssignDto.getVehicleType());
            existingTask.setEmail(taskAssignDto.getEmail());
            existingTask.setDescription(taskAssignDto.getDescription());
            existingTask.setStatus(taskAssignDto.getStatus());
            existingTask.setCustomerId(taskAssignDto.getCustomerId());
            existingTask.setSupervisor(taskAssignDto.getSupervisor());


            String customer = existingTask.getCustomerName();
            Long supervisorId = existingTask.getSupervisor();


            /*
             * Update subtasks
             */
            if(taskAssignDto.getSubTasks()!=null){

                int count = existingTask.getSubTasks().size()+1;

                for(SubTaskAssignDto subDto : taskAssignDto.getSubTasks()){

                    SubTaskAssignedEntity subTask;

                    // existing subtask
                    if(subDto.getId()!=null){

                        subTask = existingTask.getSubTasks()
                                .stream()
                                .filter(s -> s.getId()
                                        .equals(subDto.getId()))
                                .findFirst()
                                .orElse(null);

                        if(subTask == null){
                            continue;
                        }

                    }
                    // new subtask
                    else {

                        subTask = new SubTaskAssignedEntity();
                        existingTask.addSubTask(subTask);

                    }
                    /*
                     * Update only editable fields
                     */
                    subTask.setDescription(
                            subDto.getDescription()
                    );

                    subTask.setAssignedUserId(
                            subDto.getAssignedUserId()
                    );
                    /*
                     * KEEP old status
                     */
                    if(subTask.getStatus()==null){
                        subTask.setStatus("pending");
                    }
                    /*
                     * Generate sub task number only once
                     */
                    if(subTask.getUniqueSubTaskNo()==null ||
                            subTask.getUniqueSubTaskNo().isEmpty()){

                        subTask.setUniqueSubTaskNo(
                                generateSubTaskNumber(
                                        uniqueTaskNo,
                                        subTask,
                                        count
                                )
                        );

                    }
                    subTask.setSupervisor(supervisorId);
                    subTask.setMainUniqueTaskNo(uniqueTaskNo);
                    subTask.setCustomer(customer);

                    /*
                     * Update technician name
                     */
                    if(subTask.getAssignedUserId()!=null){

                        EmployeeEntity employee =
                                employeeRepository
                                        .findById(
                                                subTask.getAssignedUserId()
                                        )
                                        .orElse(null);


                        if(employee!=null){
                            subTask.setAssignUserName(
                                    employee.getFullName()
                            );
                        }

                    }
                    count++;
                }
            }
            TaskAssignEntity saved =
                    taskAssignRepository.save(existingTask);

            return taskAssignMapper.toTaskAssignDto(saved);


        }catch(Exception e){

            throw new AppException(
                    "Request Failed with Error: "+e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

        }
    }

    @Override
    public TaskAssignDto deleteData(long taskId) {
        try {
            Optional<TaskAssignEntity> optionalTaskAssignEntity = taskAssignRepository.findById(taskId);

            if(!optionalTaskAssignEntity.isPresent()){
                throw new AppException("Task Assign Does Not Exist", HttpStatus.BAD_REQUEST);
            }
            taskAssignRepository.deleteById(taskId);
            return taskAssignMapper.toTaskAssignDto(optionalTaskAssignEntity.get());
        } catch (Exception e){
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get licenceplate for customer Feedback form
    @Override
    public List<TaskAssignDto> getLicenseByDateAndCustomer(LocalDate date, Long customerId){
        List<TaskAssignEntity> list = taskAssignRepository.findLicenseByDateAndCustomer(date, customerId);
        return taskAssignMapper.toTaskAssignDtoList(list);
    }

    //get Appointments details when select the license plate no for customer feedback
    @Override
    public TaskAssignDto getDetailsByLicensePlate(LocalDate date, String licencePlate) {
        TaskAssignEntity details = taskAssignRepository.findByLicensePlate(date, licencePlate)
                .orElseThrow(() -> new AppException("Appointment not found", HttpStatus.NOT_FOUND));
        return taskAssignMapper.toTaskAssignDto(details);
    }

    @Override
    public List<TaskAssignDto> getAllDoneTasks(){
        List<TaskAssignEntity> entityList = taskAssignRepository.findByStatus("Done");
        List<TaskAssignDto> dtoList = taskAssignMapper.toTaskAssignDtoList(entityList);

        for (int i = 0; i < entityList.size(); i++) {
            TaskAssignEntity task = entityList.get(i);
            AppointmentEntity appointment =
                    appointmentRepository
                            .findByAppointmentUniqueNo(
                                    task.getAppointmentUniqueNo()
                            )
                            .orElse(null);
            if (appointment != null) {
                dtoList.get(i).setAppointmentId(appointment.getId());
                dtoList.get(i).setAppointmentUniqueNo(appointment.getAppointmentUniqueNo());
                dtoList.get(i).setCustomerName(appointment.getCustomerName());
                dtoList.get(i).setBillCreated(appointment.getBillPdf() != null);
            }else{
                dtoList.get(i).setBillCreated(false);
            }
        }
        return dtoList;
    }

    //bill Generate - update the subtask prices in subtaskAssign table
    @Override
    public void updateSubTaskPrices(Long id, TaskAssignDto dto) {
        if (dto.getSubTasks() == null) {return;}
        for(SubTaskAssignDto subTaskDto : dto.getSubTasks() ) {
            SubTaskAssignedEntity subTaskAssignedEntity = subTasksAssignRepository.findById(subTaskDto.getId())
                    .orElseThrow(() ->
                            new AppException("Sub Task not found: " + subTaskDto.getId(),HttpStatus.NOT_FOUND));

            subTaskAssignedEntity.setSubTaskPrice(
                    subTaskDto.getSubTaskPrice()
            );


            subTasksAssignRepository.save(subTaskAssignedEntity);
        }
    }

    //Bill generate and save it in appointment table
    public void generateBill(Long id) throws Exception{
        //Get task details
        TaskAssignEntity task = taskAssignRepository.findById(id)
                .orElseThrow(()->
                        new AppException("Task not Found", HttpStatus.NOT_FOUND));

        //find the related appointment
        AppointmentEntity appointment = appointmentRepository.findByAppointment_UniqueNo(task.getAppointmentUniqueNo())
                .orElseThrow(()->
                        new AppException("Appointment Not Found", HttpStatus.NOT_FOUND));

        //Create BillDto
        BillDto bill = new BillDto();

        bill.setCustomerName(task.getCustomerName());
        bill.setLicencePlate(task.getLicencePlate());
        bill.setDate(task.getDate());
        bill.setTaskName(task.getTaskName());
        bill.setServiceType(task.getServiceType());

        List<SubTaskAssignDto> billSubTasks = new ArrayList<>();
        double totalCost = 0;
        for (SubTaskAssignedEntity sub: task.getSubTasks()) {
            SubTaskAssignDto dto = new SubTaskAssignDto();
            dto.setId((sub.getId()));
            dto.setDescription(sub.getDescription());
            dto.setSubTaskPrice(sub.getSubTaskPrice());

            billSubTasks.add(dto);
            totalCost += sub.getSubTaskPrice();
        }
        bill.setSubTasks(billSubTasks);
        bill.setTotalCost(totalCost);

        //Generate pdf
        byte[] pdf = pdfGenerateService.generateBillPdf(bill);

        //Save pdf
        appointment.setBillPdf(pdf);
        appointmentRepository.save(appointment);
    }
}