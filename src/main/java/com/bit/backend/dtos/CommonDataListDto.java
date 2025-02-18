package com.bit.backend.dtos;

import java.util.List;

public class CommonDataListDto {

    private List<CommonDataDto> addedData;
    private List<CommonDataDto> removedData;

    public List<CommonDataDto> getAddedData() {
        return addedData;
    }

    public void setAddedData(List<CommonDataDto> addedData) {
        this.addedData = addedData;
    }

    public List<CommonDataDto> getRemovedData() {
        return removedData;
    }

    public void setRemovedData(List<CommonDataDto> removedData) {
        this.removedData = removedData;
    }
}
