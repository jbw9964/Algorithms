class Solution {
    public int solution(int n, int k) {

        StringBuilder sb = new StringBuilder();

        int quotient, modulo;
        while (n != 0)  {
            quotient = n / k;
            modulo = n - quotient * k;
            sb.append(modulo);
            n = quotient;
        }

        String numbers = sb.reverse().toString();

        int head = 0;
        int answer = 0;
        while (head < numbers.length())  {

            if (numbers.charAt(head) != '0')  {

                int count = 0;

                while (head + count < numbers.length() && numbers.charAt(head + count) != '0')
                count++;

                if (isPrime(Long.parseLong(numbers.substring(head, head + count))))
                answer++;

                head += count;
            }

            head++;
        }

        return answer;
    }

    public static boolean isPrime(long n) {

        boolean flag = n > 1;
        int i;
        for (i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)     {flag = false; break;}
        }

        return flag;
    }
}
