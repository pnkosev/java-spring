package app.service.services.impl;

import app.service.services.api.HashingService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class HashingServiceImpl implements HashingService {
    @Override
    public String hash(String password) {
        return DigestUtils.sha256Hex(password);
    }
}
