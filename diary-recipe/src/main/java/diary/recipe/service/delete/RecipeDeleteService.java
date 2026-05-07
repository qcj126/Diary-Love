package diary.recipe.service.delete;

import diary.common.entity.recipe.dto.req.RecipeReqDto;
import diary.common.result.ApiResponse;

import java.util.Map;

public interface RecipeDeleteService {
    ApiResponse<Map<String, Object>> deleteRecipe(RecipeReqDto recipeReqDto);
}
