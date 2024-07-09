<%-- 
    Document   : add-new-product
    Created on : Jul 7, 2024, 2:50:14 PM
    Author     : This PC
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dao.CategoryDAO" %>
<jsp:useBean id="dao" class="dao.CategoryDAO"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Add New Product</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                padding: 20px;
            }
            .form-group-flex img {
                width: 50px;
                height: 50px;
                margin-top: 10px;
                margin-right: 10px;
                object-fit: cover;
            }
            .sidebar {
                border-right: 1px solid #ddd;
                height: 100%;
            }
            .sidebar ul {
                list-style: none;
                padding: 0;
            }
            .sidebar ul li {
                padding: 10px;
                cursor: pointer;
            }

            .file-input-wrapper {
                position: relative;
                display: inline-block;
                cursor: pointer;
            }
            .file-input-wrapper input[type="file"] {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                opacity: 0;
                cursor: pointer;
            }
            .add-icon {
                display: block;
                width: 100px;
                height: 100px;
                background: url('${pageContext.request.contextPath}/image/addIcon.png') no-repeat center center;
                background-size: cover;
                text-align: center;
                line-height: 100px;
                color: #999;
                border: 1px solid #ddd;
                border-radius: 5px;
            }
            .form-group-flex {
                display: flex;
                align-items: center;
            }
            .form-group-flex label {
                min-width: 150px;
                margin-right: 10px;
            }
            .preview-container {
                display: flex;
                flex-wrap: wrap;
            }
            .preview-container img {
                width: 100px;
                height: 100px;
                object-fit: cover;
                margin: 5px;
            }
        </style>
    </head>
    <body>
        <%@include file="../layout/main-header.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-3 sidebar">
                    <div class="logo">
                        <h2>Logo</h2>
                    </div>
                    <h6 class="mt-3">Suggest add new product:</h6>
                    <ul>
                        <li>Add at least 3 photos for product photos</li>
                        <li>Add 1 photo for cover photo</li>
                        <li>Name of products have at least 25 - 100 characters</li>
                        <li>Add at least 100 characters or 1 photo on the description.</li>
                        <li>Add brands.</li>
                    </ul>
                </div>
                <div class="col-9">
                    <h3 class="mt-3">Add new product</h3>
                    <form id="addProductForm" action="add-new-product?command=ADD" method="post" enctype="multipart/form-data">
                        <div class="form-group-flex mt-3">
                            <label>Product photos</label>
                            <div class="file-input-wrapper">
                                <label class="add-icon"></label>
                                <input type="file" name="productPhotos" multiple onchange="previewFiles(event, 'productPhotosPreview')">
                            </div>
                        </div>
                        <div id="productPhotosPreview" class="preview-container"></div>

                        <div class="form-group-flex mt-3">
                            <label>Cover photos</label>
                            <div class="file-input-wrapper">
                                <label class="add-icon"></label>
                                <input type="file" name="coverPhotos" onchange="previewFiles(event, 'coverPhotosPreview')">
                            </div>
                        </div>
                        <div id="coverPhotosPreview" class="preview-container"></div>

                        <div class="mt-3">
                            <label for="productName">Name of product</label>
                            <input type="text" name="productName" id="productName" class="form-control" required>
                        </div>
                        <div class="mt-3">
                            <label for="productDescription">Description of product</label>
                            <textarea name="productDescription" id="productDescription" class="form-control" rows="5" required></textarea>
                        </div>
                        <div class="mt-3">
                            <label for="category">Category</label>
                            <select name="category" id="category" class="form-control" required>
                                <c:forEach items="${dao.getCategories()}" var="cate">
                                    <option value="${cate.getId()}">${cate.getCategoryName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="d-flex" style="justify-content: space-between;">
                            <div class="mt-3 col-6">
                                <label for="price">Price(USD)</label>
                                <input type="number" name="price" id="price" class="form-control" required>
                            </div>
                            <div class="mt-3 col-6">
                                <label for="quantity">Quantity</label>
                                <input type="number" name="quantity" id="quantity" class="form-control" required>
                                <div id="quantityError" class="error"></div>
                            </div>
                        </div>    
                        <div class="mt-3 mb-5">
                            <button type="submit" class="btn btn-success">Save</button>
                            <button type="reset" class="btn btn-secondary" onclick="resetForm()">Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="../layout/customer-footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
                                function previewFiles(event, previewId) {
                                    const input = event.target;
                                    const previewContainer = document.getElementById(previewId);
                                    previewContainer.innerHTML = ''; // Clear previous previews

                                    if (input.files) {
                                        for (const file of input.files) {
                                            const reader = new FileReader();
                                            reader.onload = function (e) {
                                                const img = document.createElement('img');
                                                img.src = e.target.result;
                                                previewContainer.appendChild(img);
                                            }

                                            reader.readAsDataURL(file);
                                        }
                                    }
                                }

                                function resetForm() {
                                    document.getElementById('productPhotosPreview').innerHTML = '';
                                    document.getElementById('coverPhotosPreview').innerHTML = '';
                                    document.getElementById('addProductForm').reset();
                                }
        </script>
    </body>
</html>

