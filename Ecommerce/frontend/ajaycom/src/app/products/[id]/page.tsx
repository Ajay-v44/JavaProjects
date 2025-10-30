'use client';

import { useEffect, useState } from 'react';
import { Product, productApi } from '@/lib/api';
import { useRouter } from 'next/navigation';
import { motion } from 'framer-motion';
import { FiArrowLeft, FiShoppingCart, FiEdit, FiTrash } from 'react-icons/fi';
import Link from 'next/link';
import { use } from 'react';

export default function ProductDetail({ params }: { params: { id: string } }) {
  // Unwrap the params Promise using React.use()
  const resolvedParams = use(params);
  const productId = resolvedParams.id;
  
  const router = useRouter();
  const [product, setProduct] = useState<Product | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const data = await productApi.getProductById(parseInt(productId));
        setProduct(data);
      } catch (error) {
        console.error('Error fetching product:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchProduct();
  }, [productId]);

  const handleDelete = async () => {
    if (!product) return;
    
    if (window.confirm('Are you sure you want to delete this product?')) {
      try {
        await productApi.deleteProduct(product.id!);
        router.push('/');
      } catch (error) {
        console.error('Error deleting product:', error);
      }
    }
  };

  if (loading) {
    return (
      <div className="container mx-auto px-4 py-8 flex justify-center items-center h-64">
        <div className="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-indigo-500"></div>
      </div>
    );
  }

  if (!product) {
    return (
      <div className="container mx-auto px-4 py-8">
        <div className="text-center">
          <h2 className="text-2xl font-bold text-gray-900 dark:text-white mb-4">Product Not Found</h2>
          <Link href="/" className="text-indigo-600 hover:text-indigo-800">
            Return to Home
          </Link>
        </div>
      </div>
    );
  }

  return (
    <div className="container mx-auto px-4 py-8">
      <Link href="/">
        <motion.button
          className="flex items-center gap-2 text-indigo-600 hover:text-indigo-800 mb-6"
          whileHover={{ x: -5 }}
          transition={{ type: 'spring', stiffness: 400, damping: 10 }}
        >
          <FiArrowLeft /> Back to Products
        </motion.button>
      </Link>

      <div className="bg-white dark:bg-gray-800 rounded-xl shadow-lg overflow-hidden p-6">
        <div className="flex flex-col md:flex-row gap-8">
          <div className="w-full md:w-1/2 bg-gray-100 dark:bg-gray-700 rounded-lg flex items-center justify-center p-8" style={{ height: '400px' }}>
            {product.imageName ? (
              <img 
                src={`http://localhost:8080/api/product/image/${product.id}`}
                alt={product.name}
                className="w-full h-full object-contain"
                onError={(e) => {
                  e.currentTarget.onerror = null;
                  e.currentTarget.style.display = 'none';
                  console.log('Error loading image, falling back to placeholder');
                }}
              />
            ) : (
              <div className="text-6xl text-gray-400">
                {product.category === 'Smartphone' ? '📱' : 
                 product.category === 'Laptop' ? '💻' : 
                 product.category === 'Tablet' ? '📟' : 
                 product.category === 'TV' ? '📺' : '🔌'}
              </div>
            )}
          </div>

          <div className="w-full md:w-1/2">
            <motion.div
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.5 }}
            >
              <div className="flex justify-between items-start">
                <div>
                  <h1 className="text-3xl font-bold text-gray-900 dark:text-white mb-2">{product.name}</h1>
                  <p className="text-lg text-gray-600 dark:text-gray-400 mb-2">by {product.brand}</p>
                </div>
                <div className="flex gap-2">
                  <Link href={`/products/edit/${product.id}`}>
                    <motion.button
                      className="p-2 bg-blue-100 text-blue-600 rounded-full"
                      whileHover={{ scale: 1.1 }}
                      whileTap={{ scale: 0.9 }}
                    >
                      <FiEdit />
                    </motion.button>
                  </Link>
                  <motion.button
                    className="p-2 bg-red-100 text-red-600 rounded-full"
                    whileHover={{ scale: 1.1 }}
                    whileTap={{ scale: 0.9 }}
                    onClick={handleDelete}
                  >
                    <FiTrash />
                  </motion.button>
                </div>
              </div>

              <div className="mt-6 mb-4">
                <span className="text-3xl font-bold text-indigo-600 dark:text-indigo-400">
                  ${product.price.toFixed(2)}
                </span>
                <span className={`ml-4 px-3 py-1 text-sm rounded-full ${
                  product.productAvailable ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'
                }`}>
                  {product.productAvailable ? `In Stock (${product.stockQuantity})` : 'Out of Stock'}
                </span>
              </div>

              <div className="border-t border-gray-200 dark:border-gray-700 py-4">
                <h3 className="text-lg font-semibold text-gray-900 dark:text-white mb-2">Description</h3>
                <p className="text-gray-600 dark:text-gray-300">{product.description}</p>
              </div>

              <div className="border-t border-gray-200 dark:border-gray-700 py-4">
                <h3 className="text-lg font-semibold text-gray-900 dark:text-white mb-2">Details</h3>
                <div className="grid grid-cols-2 gap-2">
                  <div className="text-gray-600 dark:text-gray-400">Category:</div>
                  <div className="text-gray-900 dark:text-white">{product.category}</div>
                  
                  <div className="text-gray-600 dark:text-gray-400">Release Date:</div>
                  <div className="text-gray-900 dark:text-white">
                    {product.releaseDate ? new Date(product.releaseDate).toLocaleDateString() : 'N/A'}
                  </div>
                </div>
              </div>

              <div className="mt-6">
                <motion.button
                  className="w-full bg-indigo-600 hover:bg-indigo-700 text-white py-3 px-6 rounded-lg flex items-center justify-center gap-2"
                  whileHover={{ scale: 1.02 }}
                  whileTap={{ scale: 0.98 }}
                >
                  <FiShoppingCart />
                  <span>Add to Cart</span>
                </motion.button>
              </div>
            </motion.div>
          </div>
        </div>
      </div>
    </div>
  );
}