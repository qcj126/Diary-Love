package qcj.diary.diaryrecipe.service.query;

import diary.config.result.ApiResponse;
import diary.dao.entity.recipe.dto.RecipeReqDto;

import java.util.Map;

public interface RecipeQueryService {
    ApiResponse<Map<String, Object>> queryRecipe(RecipeReqDto recipeReqDto);
}
