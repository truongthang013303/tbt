package com.world.tbt.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public class PrivilegeDTO extends AbstractDTO<PrivilegeDTO>{
    @NotBlank
    @Size(min = 1, max = 200, message
            = "name of a Privilege must be between 1 and 200 characters")
    private String name;
    @NotBlank
    @Size(min = 1, max = 200, message
            = "code of a Privilege must be between 1 and 200 characters")
    private String code;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
