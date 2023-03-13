package org.perscholas.sdbank.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
public class AccounttxnService {
}
