package qcj.diary.diaryrecipe.impl.update;

import diary.config.result.ApiResponse;
import diary.dao.entity.recipe.dto.RecipeReqDto;
import org.springframework.stereotype.Service;
import qcj.diary.diaryrecipe.service.update.RecipeUpdateService;

import java.util.Map;

@Service
public class RecipeUpdateServiceImpl implements RecipeUpdateService {
    @Override
    public ApiResponse<Map<String, Object>> updateRecipe(RecipeReqDto recipeReqDto) {
        return null;
    }
}
