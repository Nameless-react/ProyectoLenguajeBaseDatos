let slideIndex = 1;


let inputFile = document.querySelector("#image");
let imageIndexMap = new Map();
let selectedImages = [];



showSlides(slideIndex);


// Next/previous controls
function plusSlides(n) {
  showSlides(slideIndex += n);
}

// Thumbnail image controls
function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  let slides = document.querySelectorAll(".mySlides");
  let dots = document.querySelectorAll(".dot");
  if (n > slides.length) slideIndex = 1

  if (n < 1) slideIndex = slides.length

  for (let i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  for (let i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }

  slides[slideIndex - 1].style.display = "block";
  dots[slideIndex -  1].className += " active";
}


function readURL(input) {
    if (input.files && input.files[0]) {
       let reader = new FileReader();

       let imagesContainer = document.querySelector(".images-container");
       selectedImages = Array.from(input.files);


       selectedImages.forEach((image, index) => {
           let reader = new FileReader();
           let imageId = Date.now() + '_' + index;

           reader.onload = function (e) {
                let imageUrl = e.target.result;
                imageIndexMap.set(imageId, index);
                imagesContainer.innerHTML += `
                   <div class="uploaded-images-container" data-imageid="${imageId}">
                        <img class="uploaded-images" src="${imageUrl}" />
                        <div class="images-options">
                             <button type="button" onclick="deleteImage('${imageId}')"><i class="fa-solid fa-trash"></i></button>
                        </div>
                   </div>
               `;
           };

           reader.readAsDataURL(image);
       });
    }
}

function generateImageId(id) {
  return Date.now() + "_" + id;
}


async function deleteImage(imageId) {
  console.log(imageId);
  imageId = String(imageId);
  console.log(imageId)
  console.log(typeof imageId)
    let imagesContainer = document.querySelector(".images-container");
    let imageIndex = imageIndexMap.get(imageId);



    if (imageIndex !== undefined) {
         let imageContainerToRemove = document.querySelector(`.uploaded-images-container[data-imageid="${imageId}"]`);
         let imageUploadedContainers = document.querySelectorAll(".uploaded-images-container");
         imagesContainer.removeChild(imageContainerToRemove);
         let newFiles = removeFromFileList(inputFile.files, imageIndex);
         inputFile.files = newFiles;
         imageIndexMap.delete(imageId);
          await fetch(`http://localhost:8080/image-property/delete/${imageId.split("_")[1]}`, {
            method: "DELETE"

        })


         imageUploadedContainers.forEach((container, index) => {
             let updatedImageId = container.dataset.imageid.split('_')[0] + '_' + index;
             container.dataset.imageid = updatedImageId;
             let imageIndex = imageIndexMap.get(container.dataset.imageid);
             imageIndexMap.set(updatedImageId, index);

             let deleteButton = container.querySelector("button");
             deleteButton.setAttribute("onclick", `deleteImage('${updatedImageId}')`);
         });
     }
}

function removeFromFileList(fileList, indexToRemove) {
    let newFileList = new DataTransfer();
    console.log(fileList);
    console.log(fileList.length)
     if (fileList.length <= 1) return newFileList.files;

    for (let i = 0; i < fileList.length; i++) {
        if (i !== indexToRemove) {
            newFileList.items.add(fileList[i]);
        }
    }
    console.log(newFileList);

    return newFileList.files;
}