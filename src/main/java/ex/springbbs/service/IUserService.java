package ex.springbbs.service;

import ex.springbbs.dto.JwtDto;
import ex.springbbs.dto.MemberDto;
import ex.springbbs.dto.SignUpResponseDto;

public interface IUserService {
    SignUpResponseDto join(MemberDto memberDto);
    JwtDto login(MemberDto memberDto);
}
