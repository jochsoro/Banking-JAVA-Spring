package com.chonfoungo.banking.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OperationNonPermitedException extends RuntimeException {

    @Getter
    private final String errorMsg;

    @Getter
    private final String operationId;

    @Getter
    private final String source;

    @Getter
    private final String dependency;
}
