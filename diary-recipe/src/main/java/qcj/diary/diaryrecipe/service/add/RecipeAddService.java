package qcj.diary.diaryrecipe.service.add;

import diary.config.result.ApiResponse;
import diary.dao.entity.recipe.dto.RecipeReqDto;

import java.util.Map;

public interface RecipeAddService {
    ApiResponse<String> addRecipe(RecipeReqDto recipeReqDto);
}
