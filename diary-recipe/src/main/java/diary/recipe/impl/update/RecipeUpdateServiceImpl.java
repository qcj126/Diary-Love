package diary.recipe.impl.update;


import diary.common.entity.recipe.dto.req.RecipeReqDto;
import diary.common.result.ApiResponse;
import org.springframework.stereotype.Service;
import diary.recipe.service.update.RecipeUpdateService;

import java.util.Map;

@Service
public class RecipeUpdateServiceImpl implements RecipeUpdateService {
    @Override
    public ApiResponse<Map<String, Object>> updateRecipe(RecipeReqDto recipeReqDto) {
        return null;
    }
}
