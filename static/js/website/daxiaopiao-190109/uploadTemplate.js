uploadTemplate = function(i , x, ){
  let uploadTemplate = string ;

  let fileId = fileName + i;
  let fileType ;
  let fileTitle ;
  let fileName ;

  let imgId ;
  let imgUrl = 'https://img.utiexian.com/website/daxiaopiao-img/review/sahngchuan.png';

  uploadTemplate = " <label for='" + fileId + "' class='uploadImg' title='" + fileTitle + "'> "
                +=  " <input type='" + fileType + "' name=" + fileName + "' id='" + fileId + "' multiple='multiple' /> "
                +=  " <img id='" + imgId + "' src= '" + imgUrl + "' alt='uploadImg'>"
                += " </label> ";

  $(this).parent(".uploadImgRow").append(uploadTemplate);
}
