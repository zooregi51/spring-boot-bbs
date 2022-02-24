package ex.springbbs.service;

import ex.springbbs.dto.JwtDto;
import ex.springbbs.entity.Member;

public interface IJwtService {
    JwtDto issue(Member member);
    JwtDto authentication(String jwtToken);
}
