package com.example.myhealthblock.user;


import com.example.myhealthblock.user.adapter.out.UserAdapter;
import com.example.myhealthblock.user.dto.ResultSignIn;
import com.example.myhealthblock.user.dto.UserEntityDTO;
import com.example.myhealthblock.user.adapter.in.request.RequestUserSignIn;
import com.example.myhealthblock.user.adapter.in.request.RequestUserSignUp;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService implements GetUserEntityDTO{
    private final UserAdapter outport;

    public String signUp(RequestUserSignUp dto) {
        return outport.create(dto.getId(), dto.getPw(), dto.getRole()) ? "success" : "fail";
    }


    public ResultSignIn signIn(RequestUserSignIn dto) {
        User user = outport.getUser(dto.getId());
        ResultSignIn result = new ResultSignIn();

        if (user!=null && user.signIn(dto.getPw()))
            result.success(user.getRole(), user.getUid());

        return result;
    }

    @Override
    public UserEntityDTO getUserEntityDTO(String userId) {
        return outport.getUserEntityDTO(userId);
    }
}
