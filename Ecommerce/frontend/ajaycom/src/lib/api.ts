import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Configure axios instance for multipart form data (file uploads)
const fileApi = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'multipart/form-data',
  },
});

export interface Product {
  id?: number;
  name: string;
  brand: string;
  price: number;
  releaseDate?: string;
  productAvailable: boolean;
  stockQuantity: number;
  description: string;
  category: string;
  imageName?: string;
  imageType?: string;
  imageDta?: Uint8Array;
}

export const productApi = {
  // Get all products
  getAllProducts: async (): Promise<Product[]> => {
    const response = await api.get('/product');
    return response.data;
  },

  // Get a single product by ID
  getProductById: async (id: number): Promise<Product> => {
    const response = await api.get(`/product/${id}`);
    return response.data;
  },

  // Add a new product with image
  addProduct: async (product: Omit<Product, 'id'>, image?: File): Promise<Product> => {
    if (image) {
      const formData = new FormData();
      formData.append('product', new Blob([JSON.stringify(product)], { type: 'application/json' }));
      formData.append('image', image);
      
      const response = await axios.post(`${API_BASE_URL}/product`, formData, {
        headers: { 'Content-Type': 'multipart/form-data' }
      });
      return response.data;
    } else {
      const response = await api.post('/product', product);
      return response.data;
    }
  },

  // Update a product with optional image
  updateProduct: async (product: Product, image?: File): Promise<Product> => {
    if (image) {
      const formData = new FormData();
      formData.append('product', new Blob([JSON.stringify(product)], { type: 'application/json' }));
      formData.append('image', image);
      
      const response = await axios.patch(`${API_BASE_URL}/product`, formData, {
        headers: { 'Content-Type': 'multipart/form-data' }
      });
      return response.data;
    } else {
      const response = await api.patch(`/product`, product);
      return response.data;
    }
  },

  // Delete a product
  deleteProduct: async (id: number): Promise<void> => {
    await api.delete(`/product/${id}`);
  },
  
  // Search products
  searchProducts: async (query: string): Promise<Product[]> => {
    const response = await api.get(`/product/search/${query}`);
    return response.data;
  },
};

export interface OrderItem {
  productId: number;
  quantity: number;
}

export interface OrderRequest {
  customerName: string;
  email: string;
  orderItemRequests: OrderItem[];
}

export interface OrderItemResponse {
  productName: string;
  quantity: number;
  totalPrice: number;
}

export interface OrderResponse {
  orderId: string;
  customerName: string;
  email: string;
  status: string;
  orderDate: string;
  items: OrderItemResponse[];
}

export interface OrderSummary extends OrderResponse {
  itemCount: number;
}

export const orderApi = {
  // Place a new order
  placeOrder: async (orderRequest: OrderRequest): Promise<OrderResponse> => {
    const response = await api.post('/placeOrder', orderRequest);
    return response.data;
  },

  // Get all order summaries
  getOrderSummaries: async (): Promise<OrderSummary[]> => {
    const response = await api.get('/getOrderSummaries');
    return response.data;
  },
};

export default api;