package ex.springbbs.service;

import ex.springbbs.dto.JwtDto;
import ex.springbbs.dto.MemberDto;
import ex.springbbs.dto.SignUpResponseDto;
import ex.springbbs.entity.Member;
import ex.springbbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;
    private final IJwtService ijwtService;

    @Autowired
    public UserServiceImpl(UserRepository repository, IJwtService ijwtService) {
        this.repository = repository;
        this.ijwtService = ijwtService;
    }

    public SignUpResponseDto join(MemberDto memberDto){
        validateDuplicateMember(memberDto);
        Member member = memberDto.toEntity();
        repository.save(member);
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        signUpResponseDto.setEmail(memberDto.getEmail());
        return signUpResponseDto;
    }

    private void validateDuplicateMember(MemberDto memberDto) {
         Member dbMember = repository.findByEmail(memberDto.getEmail());
         if (dbMember != null) {
             throw new IllegalStateException("이미 존재하는 회원입니다.");
         }
    }

    public JwtDto login(MemberDto memberDto) {
        Member member = memberDto.toEntity();
        Member dbMember = repository.findByEmail(member.getEmail());
        if (dbMember == null)
            throw new IllegalStateException("이메일 혹은 패스워드가 틀렸습니다.");
        if (!dbMember.getPassword().equals(member.getPassword()))
            throw new IllegalStateException("이메일 혹은 패스워드가 틀렸습니다.");
        return ijwtService.issue(member);
    }
}