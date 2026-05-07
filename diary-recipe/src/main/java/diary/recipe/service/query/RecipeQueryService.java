package diary.recipe.service.query;

import diary.common.entity.recipe.dto.req.PageReqDto;
import diary.common.entity.recipe.dto.req.RecipePageReqDto;
import diary.common.entity.recipe.dto.resp.PageRespDto;
import diary.common.entity.recipe.dto.resp.RecipeRespDto;

public interface RecipeQueryService {
    PageRespDto<RecipeRespDto> pageQueryRecipe(RecipePageReqDto pageReqDto);
}
