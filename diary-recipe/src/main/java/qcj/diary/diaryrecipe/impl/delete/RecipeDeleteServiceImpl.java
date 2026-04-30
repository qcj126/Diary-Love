package qcj.diary.diaryrecipe.impl.delete;

import diary.config.result.ApiResponse;
import diary.dao.entity.recipe.dto.RecipeReqDto;
import org.springframework.stereotype.Service;
import qcj.diary.diaryrecipe.service.delete.RecipeDeleteService;

import java.util.Map;

@Service
public class RecipeDeleteServiceImpl implements RecipeDeleteService {
    @Override
    public ApiResponse<Map<String, Object>> deleteRecipe(RecipeReqDto recipeReqDto) {
        return null;
    }
}
