package moreti.springframework.spring5recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import moreti.springframework.spring5recipeapp.domain.Recipe;
import moreti.springframework.spring5recipeapp.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(String id, MultipartFile file) {
        log.info("Received a file.");
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (!recipeOptional.isPresent()) {
            log.error("Recipe not found. Id: " + id);
        } else {
            try {
                Recipe recipe = recipeOptional.get();
                Byte [] byteObjects = new Byte[file.getBytes().length];

                int i = 0;
                for (byte b : file.getBytes()) {
                    byteObjects[i++] = b;
                }
                recipe.setImage(byteObjects);
                recipeRepository.save(recipe);
            } catch (IOException e) {
                //todo handle better
                log.error("An error occurred.", e);
                e.printStackTrace();
            }
        }
    }
}
