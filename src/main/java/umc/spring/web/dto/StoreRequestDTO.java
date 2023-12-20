package umc.spring.web.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class StoreRequestDTO {

    @Getter
    public static class StoreDto{
        @NotBlank
        String name;
        @Size(min=5, max=12)
        String address;
        @NotNull
        Float score;
        List<Long> reviewList;
    }

    @Getter
    public static class ReviewDto {
        @NotBlank
        String title;
        @NotNull
        Float score;
        @NotBlank
        String content;
    }

    @Getter
    public static class MissionDto{
        @NotNull
        private Integer reward;
        @NotNull
        private Integer price;
        @NotNull
        private LocalDate deadline;
        @NotNull
        private String missionSpec;
    }

    @Getter
    public static class ChallengeMissionDTO{
        @NotBlank
        Long id;
    }
}
