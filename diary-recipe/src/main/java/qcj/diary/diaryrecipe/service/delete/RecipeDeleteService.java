package qcj.diary.diaryrecipe.service.delete;

import diary.config.result.ApiResponse;
import diary.dao.entity.recipe.dto.RecipeReqDto;

import java.util.Map;

public interface RecipeDeleteService {
    ApiResponse<Map<String, Object>> deleteRecipe(RecipeReqDto recipeReqDto);
}
