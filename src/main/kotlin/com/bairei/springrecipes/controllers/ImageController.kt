package com.bairei.springrecipes.controllers

import com.bairei.springrecipes.services.ImageService
import com.bairei.springrecipes.services.RecipeService
import org.apache.tomcat.util.http.fileupload.IOUtils
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import javax.servlet.http.HttpServletResponse

@Controller
class ImageController (private val imageService: ImageService,
                       private val recipeService: RecipeService) {

    @GetMapping("/recipe/{id}/image")
    fun showUploadForm(@PathVariable id: String, model: Model): String{
        model.addAttribute("recipe", recipeService.findCommandById(id.toLong()))

        return "recipe/imageuploadform"
    }

    @PostMapping("/recipe/{id}/image")
    fun handleImagePost(@PathVariable id: String, @RequestParam("imagefile") file: MultipartFile): String{

        imageService.saveImageFile(id.toLong(), file)

        return "redirect:/recipe/$id/show"
    }

    @GetMapping("recipe/{id}/recipeimage")
    fun renderImageFromDB(@PathVariable id: String, response: HttpServletResponse){
        val recipeCommand = recipeService.findCommandById(id.toLong())

        if(recipeCommand!!.image.isNotEmpty()){
            val byteArray = ByteArray(recipeCommand.image.size)

            var i = 0
            for (wrappedByte in recipeCommand.image){
                byteArray[i++] = wrappedByte
            }

        response.contentType = "image/jpeg"
        val inputStream = ByteArrayInputStream(byteArray)
        IOUtils.copy(inputStream, response.outputStream)
        }
    }

}