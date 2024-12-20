package com.rlti.hexagonal.pessoa.adapters.input.request;

import com.rlti.hexagonal.util.SortFieldResolver;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaginationRequest {
    private Integer pageNumber = 0;
    private Integer pageSize = 10;
    private String sortBy;
    private String sortDirection = "ASC";
    private String context;

    public String getValidatedSortBy() {
        if (sortBy == null || SortFieldResolver.isValidSortField(context, sortBy)) {
            return SortFieldResolver.getDefaultSortField(context);
        }
        return sortBy;
    }
}
