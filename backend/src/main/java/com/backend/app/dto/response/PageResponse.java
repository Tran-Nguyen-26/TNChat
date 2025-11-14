package com.backend.app.dto.response;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T> {
  private List<T> data;

  @JsonProperty("page")
  private PageMetaData metaData;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class PageMetaData {
    private int number;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
    private boolean empty;
  }

  public static <T> PageResponse<T> fromPage(Page<T> page) {
    return PageResponse.<T>builder()
      .data(page.getContent())
      .metaData(PageMetaData.builder()
        .number(page.getNumber())  
        .size(page.getSize())
        .totalElements(page.getTotalElements())
        .totalPages(page.getTotalPages())
        .first(page.isFirst())
        .last(page.isLast())
        .empty(page.isEmpty())
        .build()
      )
      .build();
  }
}
