package com.winiardev.nails.security.controller.response;

import java.util.Date;

public record ResponseAuthentication(String token, Date expirationDate) {
}
