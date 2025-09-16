// 代码生成时间: 2025-09-17 05:53:30
public class ShoppingCartService {

    // ShoppingCart 实体类，用于表示购物车中的商品项
    private class ShoppingCartItem {
        private String productId;
        private String productName;
        private double price;
        private int quantity;

        public ShoppingCartItem(String productId, String productName, double price, int quantity) {
            super();
            this.productId = productId;
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }

        // Getter 和 Setter 方法
        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    // 购物车列表，存储购物车中的所有商品项
    private List<ShoppingCartItem> cartItems = new ArrayList<>();

    // 添加商品到购物车
    public void addToCart(String productId, String productName, double price, int quantity) {
        // 检查商品是否存在于购物车中
        for (int i = 0; i < cartItems.size(); i++) {
            ShoppingCartItem item = cartItems.get(i);
            if (item.getProductId().equals(productId)) {
                // 商品已存在，增加数量
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // 商品不存在，添加新的商品项
        cartItems.add(new ShoppingCartItem(productId, productName, price, quantity));
    }

    // 从购物车中移除商品
    public void removeFromCart(String productId) {
        // 遍历购物车商品项列表，移除指定商品
        cartItems.removeIf(item -> item.getProductId().equals(productId));
    }

    // 获取购物车中的所有商品项
    public List<ShoppingCartItem> getCartItems() {
        return cartItems;
    }

    // 清空购物车
    public void clearCart() {
        cartItems.clear();
    }

    // 计算购物车中所有商品的总价格
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (ShoppingCartItem item : cartItems) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    // 显示购物车内容
    public void displayCart() {
        for (ShoppingCartItem item : cartItems) {
            System.out.println("Product ID: " + item.getProductId() + ", Product Name: " + item.getProductName() + ", Price: " + item.getPrice() + ", Quantity: " + item.getQuantity());
        }
    }
}
