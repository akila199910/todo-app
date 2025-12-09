import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'
import { defineConfig as defineVitestConfig } from 'vitest/config'

export default defineVitestConfig({
  plugins: [react(), tailwindcss()],
  test: {
    setupFiles: "./src/setupTests.ts",
    environment: 'jsdom',
    globals: true,

  },
})
