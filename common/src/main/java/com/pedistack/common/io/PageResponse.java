package com.pedistack.common.io;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PageResponse<T> implements Serializable {

  private final Integer totalNumberOfPages;
  private final Integer pageNumber;
  private final Integer pageSize;
  private final Long totalElements;
  private final List<T> pageContents;

  public PageResponse() {
    this.pageContents = null;
    this.pageNumber = null;
    this.pageSize = null;
    this.totalElements = null;
    this.totalNumberOfPages = null;
  }

  private PageResponse(
      final List<T> pageContents,
      final Integer pageNumber,
      final Integer pageSize,
      final Long totalElements,
      final Integer totalNumberOfPages) {
    this.pageContents = pageContents;
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.totalElements = totalElements;
    this.totalNumberOfPages = totalNumberOfPages;
  }

  private PageResponse(
      final List<T> pageContents,
      final Integer pageNumber,
      final Integer pageSize,
      final Long totalElements) {
    this.pageContents = pageContents;
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.totalElements = totalElements;
    this.totalNumberOfPages = null;
  }

  private PageResponse(final Integer pageNumber, final Integer pageSize, final Long totalElements) {
    this.pageContents = null;
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.totalElements = totalElements;
    this.totalNumberOfPages = null;
  }

  public static <T> PageResponse<T> create(
      final List<T> pageContents,
      final Integer pageNumber,
      final Integer pageSize,
      final Long totalElements) {
    return new PageResponse<>(pageContents, pageNumber, pageSize, totalElements);
  }

  public static <T> PageResponse<T> create(
      final Integer pageNumber, final Integer pageSize, final Long totalElements) {
    return new PageResponse<>(pageNumber, pageSize, totalElements);
  }

  public static <T> PageResponse<T> create(
      final List<T> pageContents,
      final Integer pageNumber,
      final Integer pageSize,
      final Long totalElements,
      final Integer totalNumberOfPages) {
    return new PageResponse<>(pageContents, pageNumber, pageSize, totalElements, totalNumberOfPages);
  }

  public Integer getPageNumber() {
    return pageNumber;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public List<T> getPageContents() {
    return pageContents;
  }

  public Long getTotalElements() {
    return totalElements;
  }

  public Integer getTotalNumberOfPages() {
    return totalNumberOfPages;
  }
}
