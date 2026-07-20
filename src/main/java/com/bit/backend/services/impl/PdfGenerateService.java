package com.bit.backend.services.impl;

import com.bit.backend.dtos.SubTaskAssignDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.springframework.stereotype.Service;
import com.bit.backend.dtos.BillDto;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;

@Service
public class PdfGenerateService {



    private static final Font TITLE_FONT =
            new Font(
                    Font.FontFamily.HELVETICA,
                    32,
                    Font.BOLD,
                    new BaseColor(0, 102, 204)
            );


    private static final Font HEADER_FONT =
            new Font(
                    Font.FontFamily.HELVETICA,
                    12,
                    Font.BOLD
            );

    private static final Font NORMAL_MIN_FONT =
            new Font(
                    Font.FontFamily.HELVETICA,
                    8
            );

    private static final Font NORMAL_FONT =
            new Font(
                    Font.FontFamily.HELVETICA,
                    10
            );


    private static final Font TOTAL_FONT =
            new Font(
                    Font.FontFamily.HELVETICA,
                    14,
                    Font.BOLD
            );
    public byte[] generateBillPdf(BillDto billDto) throws Exception {

        ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, outputStream);

        document.open();

        //Title
        Paragraph title = new Paragraph("Namal Service Center", TITLE_FONT);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(
                new Paragraph("\n")
        );

        PdfPTable headerTable =
                new PdfPTable(2);


        headerTable.setWidthPercentage(100);


//        headerTable.setWidths(
//                new float[]{3,1}
//        );

        headerTable.setWidths(new float[]{2.2f, 1.8f});

        // Empty left cell
        PdfPCell emptyCell = new PdfPCell(
                new Phrase("")
        );

        emptyCell.setBorder(Rectangle.NO_BORDER);

        headerTable.addCell(emptyCell);
//
//        // Service center information
//
//        Paragraph info =
//                new Paragraph(
//                        "Telephone : 0776676323\n" +
//                                "Email : namalvehicleservice@gmail.com\n" +
//                                "Address : Namal Service Center, \nKegalle Road, \nDhaluggala, \nRabukkana.\n\n",
//                        NORMAL_MIN_FONT
//                );
//
//        document.add(
//                new Paragraph("\n")
//        );
//
//
//        PdfPCell infoCell =
//                new PdfPCell(info);
//
//
//        infoCell.setBorder(Rectangle.NO_BORDER);
//
//        infoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//
//        headerTable.addCell(infoCell);
//
//        document.add(headerTable);

        PdfPTable infoTable = new PdfPTable(1);
        infoTable.setWidthPercentage(100);

        //Service Center Information
        PdfPCell info = new PdfPCell(
                new Phrase(
                        "Telephone : 077-6676323\n\n" +
                                "Email : namalvehicleservice@gmail.com\n\n" +
                                "Address : Namal Service Center,\n" +
                                "Kegalle Road,\n" +
                                "Dhaluggala,\n" +
                                "Rabukkana",
                        NORMAL_FONT
                )
        );

        PdfPCell infoCell = new PdfPCell(info);

        infoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        infoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        infoCell.setPaddingTop(10);
        infoCell.setPaddingBottom(10);
        infoCell.setPaddingLeft(12);
        infoCell.setPaddingRight(12);

// This creates the black rectangle like your sketch
        infoCell.setBorder(Rectangle.BOX);
        infoCell.setBorderWidth(1.5f);

        infoTable.addCell(infoCell);

        PdfPCell rightCell = new PdfPCell();
        rightCell.setBorder(Rectangle.NO_BORDER);
        rightCell.addElement(infoTable);

        headerTable.addCell(rightCell);
        document.add(headerTable);


        //Blue line
        LineSeparator line =
                new LineSeparator();


        line.setLineColor(
                new BaseColor(0,102,204)
        );


        document.add(line);



        document.add(
                new Paragraph("\n")
        );

        Paragraph customerDetails =
                new Paragraph(
                        "Customer Name : "
                                + billDto.getCustomerName(),
                        HEADER_FONT
                );


        document.add(
                customerDetails
        );

        Paragraph vehicleDetails =
                new Paragraph(
                        "License Plate : "
                                + billDto.getLicencePlate()
                                + "\nDate : "
                                + billDto.getDate()
                                + "\nTask Name : "
                                + billDto.getTaskName()
                                + "\nService Type : "
                                + billDto.getServiceType(),
                        HEADER_FONT
                );


        document.add(
                vehicleDetails
        );



        document.add(
                new Paragraph("\n")
        );

        //Bill Item Table
        PdfPTable billTable =
                new PdfPTable(2);


        billTable.setWidthPercentage(
                100
        );



        PdfPCell descriptionHeader =
                new PdfPCell(
                        new Phrase(
                                "Description",
                                HEADER_FONT
                        )
                );
        descriptionHeader.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell amountHeader =
                new PdfPCell(
                        new Phrase(
                                "Amount",
                                HEADER_FONT
                        )
                );
        amountHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);



        billTable.addCell(
                descriptionHeader
        );


        billTable.addCell(
                amountHeader
        );

        for(SubTaskAssignDto item :
                billDto.getSubTasks()) {


//            billTable.addCell(
//                    new Phrase(
//                            item.getDescription(),
//                            NORMAL_FONT
//                    )
//            );
            PdfPCell descriptionCell = new PdfPCell(
                    new Phrase(
                            item.getDescription(),
                            NORMAL_FONT
                    )
            );
            descriptionCell.setHorizontalAlignment(Element.ALIGN_CENTER);

            billTable.addCell(descriptionCell);


//            billTable.addCell(
//                    new Phrase(
//                            "Rs. "
//                                    + item.getSubTaskPrice(),
//                            NORMAL_FONT
//                    )
//            );

            DecimalFormat df = new DecimalFormat("0.00");
            PdfPCell amountCell = new PdfPCell(
                    new Phrase(
                            "Rs. " + df.format(item.getSubTaskPrice()),
                            NORMAL_FONT
                    )
            );
            amountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

            billTable.addCell(amountCell);

        }

        document.add(
                billTable
        );



        document.add(
                new Paragraph("\n")
        );

        //total
        DecimalFormat df = new DecimalFormat("0.00");
        Paragraph total =
                new Paragraph(
                        "Total : Rs. "
                                + df.format(billDto.getTotalCost()),
                        TOTAL_FONT
                );


        total.setAlignment(
                Element.ALIGN_RIGHT
        );


        document.add(
                total
        );

        document.add(
                new Paragraph("\n\n")
        );

        //Black line
        LineSeparator blackLine =
                new LineSeparator();


        blackLine.setLineColor(
                new BaseColor(63,63,63)
        );


        document.add(blackLine);

        //Footer Message
        document.add(
                new Paragraph("\n\n")
        );


        Paragraph thankYou =
                new Paragraph(
                        "Thank you for choosing Namal Service Center!",
                        NORMAL_FONT
                );


        thankYou.setAlignment(
                Element.ALIGN_CENTER
        );


        document.add(
                thankYou
        );



        document.close();



        return outputStream.toByteArray();
    }

}
