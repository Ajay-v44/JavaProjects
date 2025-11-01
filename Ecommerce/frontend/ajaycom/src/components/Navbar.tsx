'use client';

import Link from 'next/link';
import { FiShoppingCart, FiMenu, FiX, FiPackage } from 'react-icons/fi';
import { useState } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { useCart } from '@/lib/cartContext';

export default function Navbar() {
  const [isOpen, setIsOpen] = useState(false);
  const { totalItems } = useCart();

  return (
    <nav className="bg-white dark:bg-gray-900 shadow-md">
      <div className="container mx-auto px-4">
        <div className="flex justify-between items-center h-16">
          <Link href="/" className="flex items-center">
            <span className="text-2xl font-bold text-indigo-600 dark:text-indigo-400">AjayCom</span>
          </Link>

          {/* Desktop Navigation */}
          <div className="hidden md:flex items-center space-x-8">
            <Link href="/" className="text-gray-700 dark:text-gray-200 hover:text-indigo-600 dark:hover:text-indigo-400">
              Products
            </Link>
            <Link href="/products/add" className="text-gray-700 dark:text-gray-200 hover:text-indigo-600 dark:hover:text-indigo-400">
              Add Product
            </Link>
            <Link href="/cart" className="relative">
              <FiShoppingCart className="h-6 w-6 text-gray-700 dark:text-gray-200 hover:text-indigo-600 dark:hover:text-indigo-400" />
              {totalItems > 0 && (
                <motion.span 
                  initial={{ scale: 0 }}
                  animate={{ scale: 1 }}
                  className="absolute -top-2 -right-2 bg-indigo-600 text-white text-xs rounded-full h-5 w-5 flex items-center justify-center"
                >
                  {totalItems}
                </motion.span>
              )}
            </Link>
            <Link href="/orders" className="text-gray-700 dark:text-gray-200 hover:text-indigo-600 dark:hover:text-indigo-400 flex items-center gap-1">
              <FiPackage className="h-5 w-5" />
              <span>Orders</span>
            </Link>
          </div>

          {/* Mobile Navigation Button */}
          <div className="md:hidden">
            <button
              onClick={() => setIsOpen(!isOpen)}
              className="text-gray-700 dark:text-gray-200 hover:text-indigo-600 dark:hover:text-indigo-400"
            >
              {isOpen ? <FiX className="h-6 w-6" /> : <FiMenu className="h-6 w-6" />}
            </button>
          </div>
        </div>
      </div>

      {/* Mobile Navigation Menu */}
      <AnimatePresence>
        {isOpen && (
          <motion.div
            initial={{ opacity: 0, height: 0 }}
            animate={{ opacity: 1, height: 'auto' }}
            exit={{ opacity: 0, height: 0 }}
            className="md:hidden bg-white dark:bg-gray-900 shadow-lg"
          >
            <div className="container mx-auto px-4 py-3 space-y-3">
              <Link 
                href="/" 
                className="block text-gray-700 dark:text-gray-200 hover:text-indigo-600 dark:hover:text-indigo-400"
                onClick={() => setIsOpen(false)}
              >
                Products
              </Link>
              <Link 
                href="/products/add" 
                className="block text-gray-700 dark:text-gray-200 hover:text-indigo-600 dark:hover:text-indigo-400"
                onClick={() => setIsOpen(false)}
              >
                Add Product
              </Link>
              <Link 
                href="/cart" 
                className="block text-gray-700 dark:text-gray-200 hover:text-indigo-600 dark:hover:text-indigo-400"
                onClick={() => setIsOpen(false)}
              >
                Cart ({totalItems})
              </Link>
              <Link 
                href="/orders" 
                className="block text-gray-700 dark:text-gray-200 hover:text-indigo-600 dark:hover:text-indigo-400"
                onClick={() => setIsOpen(false)}
              >
                Orders
              </Link>
            </div>
          </motion.div>
        )}
      </AnimatePresence>
    </nav>
  );
}