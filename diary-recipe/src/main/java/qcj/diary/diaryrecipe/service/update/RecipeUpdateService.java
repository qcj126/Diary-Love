package qcj.diary.diaryrecipe.service.update;

import diary.config.result.ApiResponse;
import diary.dao.entity.recipe.dto.RecipeReqDto;

import java.util.Map;

public interface RecipeUpdateService {
    ApiResponse<Map<String, Object>> updateRecipe(RecipeReqDto recipeReqDto);
}
