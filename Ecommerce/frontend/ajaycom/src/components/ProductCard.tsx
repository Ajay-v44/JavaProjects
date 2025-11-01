'use client';

import { Product } from '@/lib/api';
import { motion, AnimatePresence } from 'framer-motion';
import { FiShoppingCart, FiCheck } from 'react-icons/fi';
import Link from 'next/link';
import { useCart } from '@/lib/cartContext';
import { useState } from 'react';

interface ProductCardProps {
  product: Product;
}

export default function ProductCard({ product }: ProductCardProps) {
  const { addToCart } = useCart();
  const [showToast, setShowToast] = useState(false);

  const handleAddToCart = () => {
    if (product.productAvailable) {
      addToCart(product, 1);
      setShowToast(true);
      setTimeout(() => setShowToast(false), 2000);
    }
  };

  return (
    <motion.div
      className="bg-white dark:bg-gray-800 rounded-xl shadow-lg overflow-hidden relative"
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.3 }}
      whileHover={{ y: -5, boxShadow: "0 10px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04)", transition: { duration: 0.2 } }}
    >
      {/* Toast Notification */}
      <AnimatePresence>
        {showToast && (
          <motion.div 
            className="absolute top-2 right-2 bg-green-500 text-white px-3 py-2 rounded-lg shadow-lg z-10 flex items-center gap-2"
            initial={{ opacity: 0, y: -10 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: -10 }}
          >
            <FiCheck />
            <span>Added to cart!</span>
          </motion.div>
        )}
      </AnimatePresence>

      {product.imageName && (
        <div className="w-full h-48 overflow-hidden">
          <img 
            src={`http://localhost:8080/api/product/image/${product.id}`}
            alt={product.name}
            className="w-full h-full object-cover"
            onError={(e) => {
              e.currentTarget.onerror = null;
              e.currentTarget.style.display = 'none';
            }}
          />
        </div>
      )}
      <div className="p-5">
        <h3 className="text-xl font-bold text-gray-900 dark:text-white mb-1">
          {product.name}
        </h3>
        <p className="text-sm text-gray-500 dark:text-gray-400 italic mb-3">by {product.brand}</p>
        
        <div className="flex justify-between items-center mb-4">
          <span className="text-2xl font-bold text-indigo-600 dark:text-indigo-400">
            ${product.price.toFixed(2)}
          </span>
          <span className={`px-2 py-1 text-xs rounded-full ${
            product.productAvailable ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'
          }`}>
            {product.productAvailable ? 'In Stock' : 'Out of Stock'}
          </span>
        </div>
        
        <p className="text-gray-600 dark:text-gray-300 text-sm line-clamp-2 mb-4">
          {product.description}
        </p>
        
        <div className="flex justify-between items-center">
          <Link 
            href={`/products/${product.id}`}
            className="text-indigo-600 dark:text-indigo-400 hover:text-indigo-800 dark:hover:text-indigo-300 text-sm font-medium"
          >
            View Details
          </Link>
          
          <motion.button
            className={`${product.productAvailable 
              ? 'bg-indigo-600 hover:bg-indigo-700' 
              : 'bg-gray-400 cursor-not-allowed'} 
              text-white px-3 py-2 rounded-lg flex items-center gap-2`}
            whileTap={product.productAvailable ? { scale: 0.95 } : {}}
            onClick={handleAddToCart}
            disabled={!product.productAvailable}
          >
            <FiShoppingCart />
            <span>Add to Cart</span>
          </motion.button>
        </div>
      </div>
    </motion.div>
  );
}