package br.com.dominio.personapi.controller.exception;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
}
