package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.validation.annotation.ExistMembers;
import umc.spring.validation.annotation.ExistMissions;
import umc.spring.validation.annotation.ExistRegion;
import umc.spring.validation.annotation.ExistStores;

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
        Integer reward;
        @NotNull
        Integer price;
        @NotNull
        LocalDate deadline;
        @NotNull
        String missionSpec;
    }
}
