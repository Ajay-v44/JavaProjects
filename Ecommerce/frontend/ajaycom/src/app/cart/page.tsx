'use client';

import { motion } from 'framer-motion';
import { FiArrowLeft, FiShoppingBag } from 'react-icons/fi';
import Link from 'next/link';

export default function Cart() {
  return (
    <div className="container mx-auto px-4 py-8">
      <Link href="/">
        <motion.button
          className="flex items-center gap-2 text-indigo-600 hover:text-indigo-800 mb-6"
          whileHover={{ x: -5 }}
          transition={{ type: 'spring', stiffness: 400, damping: 10 }}
        >
          <FiArrowLeft /> Continue Shopping
        </motion.button>
      </Link>

      <motion.div
        className="bg-white dark:bg-gray-800 rounded-xl shadow-lg overflow-hidden p-6"
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.3 }}
      >
        <h1 className="text-2xl font-bold text-gray-900 dark:text-white mb-6">Your Cart</h1>
        
        <div className="flex flex-col items-center justify-center py-12">
          <FiShoppingBag className="text-6xl text-gray-300 mb-4" />
          <p className="text-gray-500 dark:text-gray-400 text-lg mb-6">Your cart is empty</p>
          <Link href="/">
            <motion.button
              className="bg-indigo-600 hover:bg-indigo-700 text-white px-6 py-3 rounded-lg"
              whileHover={{ scale: 1.05 }}
              whileTap={{ scale: 0.95 }}
            >
              Browse Products
            </motion.button>
          </Link>
        </div>
      </motion.div>
    </div>
  );
}