package com.techlabs.mappings.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PageResponse<T> {

	private int totalPages;
	private long totalElements;
	private int size;
	private List<T> contents;
	private boolean lastPage;
}
