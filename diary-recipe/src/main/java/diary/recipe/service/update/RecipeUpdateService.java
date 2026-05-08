package diary.recipe.service.update;

import diary.common.entity.recipe.dto.req.RecipeReqDto;
import diary.common.result.ApiResponse;

import java.util.Map;

public interface RecipeUpdateService {
    ApiResponse<String> updateRecipe(RecipeReqDto recipeReqDto);
}
