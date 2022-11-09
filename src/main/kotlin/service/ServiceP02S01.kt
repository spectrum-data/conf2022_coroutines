package service

sealed class ServiceP02S01 {
    class Counter {
        private var counter: Int = 0

        val currentValue: Int
            get() = counter

        fun add(number: Int) {
            counter += number
        }
    }
}
