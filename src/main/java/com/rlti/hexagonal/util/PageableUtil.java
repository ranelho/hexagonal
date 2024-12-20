package com.rlti.hexagonal.util;

import com.rlti.hexagonal.pessoa.adapters.input.request.PaginationRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtil {
    // Impede a criação de instâncias da classe
    private PageableUtil() {
        throw new UnsupportedOperationException("Esta classe não pode ser instanciada.");
    }

    /**
     * Cria um Pageable com base nos parâmetros fornecidos.
     *
     * @param pageNumber     Número da página (default: 0)
     * @param pageSize       Tamanho da página (default: 20)
     * @param sortBy         Campo pelo qual ordenar os resultados
     * @param sortDirection  Direção da ordenação (ASC ou DESC) (default: ASC)
     * @return Pageable      O objeto Pageable configurado
     */
    public static Pageable createPageable(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        // Define a direção de ordenação padrão como ASC caso não seja especificado
        Sort.Direction direction = "DESC".equalsIgnoreCase(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC;

        // Retorna um Pageable com os valores fornecidos, ou valores padrão se nulos
        return PageRequest.of(
                pageNumber != null ? pageNumber : 0,  // Padrão: página 0
                pageSize != null ? pageSize : 20,     // Padrão: 20 itens por página
                Sort.by(direction, sortBy != null ? sortBy : "id")  // Padrão: ordenação por "id"
        );
    }

    /**
     * Cria um Pageable com base no objeto PaginationRequest e o contexto fornecido.
     *
     * @param paginationRequest O objeto que contém os parâmetros de paginação
     * @param context           O contexto para determinar o campo de ordenação padrão
     * @return Pageable         O objeto Pageable configurado
     */
    public static Pageable getPageable(PaginationRequest paginationRequest, String context) {
        // Verifica se o sortBy é válido, caso contrário, usa o valor padrão do contexto
        String sortBy = paginationRequest.getSortBy();
        if (sortBy == null || SortFieldResolver.isValidSortField(context, sortBy)) {
            sortBy = SortFieldResolver.getDefaultSortField(context);  // Fallback para o campo padrão
        }

        // Cria o Pageable com os parâmetros fornecidos
        return createPageable(
                paginationRequest.getPageNumber(),
                paginationRequest.getPageSize(),
                sortBy,
                paginationRequest.getSortDirection()
        );
    }
}
