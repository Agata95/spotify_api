package spotifyapi.spotify_api.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountPasswordResetRequest {

    //    dto - data transfer object

    //    te parametry muszą się zgadzać z tymi z pliku account-passwordreset
//    th:name="accountId" oraz th:name="resetpassword":
    private Long accountId;

    @NotEmpty
    @Size(min = 4, max = 72)
    private String resetpassword;
}
