package com.kasperin.inventory_management.validator_services;

import javax.validation.Payload;

public class Severity {
    public interface Info extends Payload {
    }

    public interface Error extends Payload {
    }
}
