package qcj.diary.diaryrecipe.impl.add;

import diary.config.result.ApiResponse;
import diary.dao.entity.recipe.ao.RecipeIngredientAO;
import diary.dao.entity.recipe.ao.RecipeStepAO;
import diary.dao.entity.recipe.dto.RecipeReqDto;
import diary.dao.entity.recipe.po.RecipeIngredientPO;
import diary.dao.entity.recipe.po.RecipePO;
import diary.dao.entity.recipe.po.RecipeStepPO;
import diary.dao.mapper.recipe.RecipeIngredientMapper;
import diary.dao.mapper.recipe.RecipeMapper;
import diary.dao.mapper.recipe.RecipeStepMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import qcj.diary.diaryrecipe.service.add.RecipeAddService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeAddServiceImpl implements RecipeAddService {
    @Resource
    private RecipeMapper recipeMapper;
    
    @Resource
    private RecipeIngredientMapper recipeIngredientMapper;
    
    @Resource
    private RecipeStepMapper recipeStepMapper;
    
    @Override
    public ApiResponse<String> addRecipe(RecipeReqDto recipeReqDto) {
        if (recipeReqDto == null) return ApiResponse.fail(400, "入参为空");
        if (recipeReqDto.getCoupleId() == null || recipeReqDto.getTitle() == null ||
            recipeReqDto.getCategory() == null || recipeReqDto.getMealType() == null ||
            recipeReqDto.getIngredients() == null || recipeReqDto.getSteps() == null) {
            return ApiResponse.fail(400, "食谱存在必填参数为空");
        }
        List<RecipeIngredientAO> ingredients = recipeReqDto.getIngredients();
        ingredients.stream().filter(ingredient -> ingredient.getName() == null || ingredient.getQuantity() == null)
            .findAny().ifPresent(ingredient -> {throw new RuntimeException("食材存在必填参数为空");});

        List<RecipeStepAO> steps = recipeReqDto.getSteps();
        steps.stream().filter(step -> step.getDescription() == null || step.getStepNumber() == null)
            .findAny().ifPresent(step -> {throw new RuntimeException("步骤存在必填参数为空");});

        RecipePO recipePO = new RecipePO();
        List<RecipeIngredientPO> recipeIngredientPOs = new ArrayList<>();
        List<RecipeStepPO> recipeStepPOs = new ArrayList<>();
        // 填充元素
        recipePO.setAuthorId(recipeReqDto.getAuthorId());
        recipePO.setCoupleId(recipeReqDto.getCoupleId());
        recipePO.setTitle(recipeReqDto.getTitle());
        recipePO.setCoverImg(recipeReqDto.getCoverImg());
        recipePO.setDescription(recipeReqDto.getDescription());
        recipePO.setCategory(recipeReqDto.getCategory());
        recipePO.setMealType(recipeReqDto.getMealType());
        recipePO.setDifficulty(recipeReqDto.getDifficulty());
        recipePO.setCookingTime(recipeReqDto.getCookingTime());
        recipePO.setStory(recipeReqDto.getStory());
        recipePO.setIsAnniversary(recipeReqDto.getIsAnniversary());
        recipePO.setAnniversaryDate(recipeReqDto.getAnniversaryDate());
        recipePO.setStatus(recipeReqDto.getStatus()); // 已发布
        recipePO.setViewCount(recipePO.getViewCount());
        recipePO.setLikeCount(recipePO.getLikeCount());
        recipePO.setCookCount(recipePO.getCookCount());
        recipePO.setIsAnniversary(recipePO.getIsAnniversary());
        recipePO.setAnniversaryDate(recipePO.getAnniversaryDate());
        recipePO.setCreatedAt(LocalDateTime.now());
        recipePO.setUpdatedAt(LocalDateTime.now());

        for (RecipeIngredientAO ao : ingredients) {
            RecipeIngredientPO po = new RecipeIngredientPO();
            po.setRecipeId(recipeReqDto.getRecipeId());
            po.setName(ao.getName());
            po.setQuantity(ao.getQuantity());
            po.setIsMain(ao.getIsMain());
            po.setSortOrder(ao.getSortOrder());
            recipeIngredientPOs.add(po);
        }

        for (RecipeStepAO ao : steps) {
            RecipeStepPO po = new RecipeStepPO();
            po.setRecipeId(recipeReqDto.getRecipeId());
            po.setStepNumber(ao.getStepNumber());
            po.setDescription(ao.getDescription());
            po.setImageUrl(ao.getImageUrl());
            po.setTimerMin(ao.getTimerMin());
            po.setSortOrder(ao.getSortOrder());
            recipeStepPOs.add(po);
        }

        recipeMapper.insert(recipePO);
        recipeIngredientMapper.batchInsert(recipeIngredientPOs);
        recipeStepMapper.batchInsert(recipeStepPOs);
        
        return ApiResponse.success("食谱添加成功");
    }
}
