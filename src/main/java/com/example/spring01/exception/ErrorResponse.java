package com.example.spring01.exception;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ApiModel
public class ErrorResponse {
	//@ApiModelProperty(value = "status", example = "status")
    private int status;
	//@ApiModelProperty(value = "data", example = "data")
    private Object data;
	//@ApiModelProperty(value = "message", example = "message")
    private String message;
}
