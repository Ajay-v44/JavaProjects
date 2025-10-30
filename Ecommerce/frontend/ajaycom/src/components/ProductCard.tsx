'use client';

import { Product } from '@/lib/api';
import { motion } from 'framer-motion';
import { FiShoppingCart } from 'react-icons/fi';
import Link from 'next/link';

interface ProductCardProps {
  product: Product;
}

export default function ProductCard({ product }: ProductCardProps) {
  return (
    <motion.div
      className="bg-white dark:bg-gray-800 rounded-xl shadow-lg overflow-hidden"
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.3 }}
      whileHover={{ y: -5, transition: { duration: 0.2 } }}
    >
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
            className="bg-indigo-600 hover:bg-indigo-700 text-white px-3 py-2 rounded-lg flex items-center gap-2"
            whileTap={{ scale: 0.95 }}
          >
            <FiShoppingCart />
            <span>Add to Cart</span>
          </motion.button>
        </div>
      </div>
    </motion.div>
  );
}