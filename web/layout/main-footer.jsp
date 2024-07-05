<%-- 
    Document   : main-footer
    Created on : Jun 2, 2024, 5:38:41 PM
    Author     : hoang hung 
--%>
    </body>
    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', function() {
            function formatCurrency(price) {
                return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(price);
            }
            
            let priceTags = document.querySelectorAll("#price");
            
            priceTags.forEach(tag => {
               let formatCurrent = formatCurrency(tag.textContent);
               
               tag.textContent = formatCurrent;
            });
        })
    </script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</html>
