; Exercise 5: Caclulate the first seven Fibonacci numbers.

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

; change NUM to the value that you want to run the program with 
NUM = 20

.data
quadArray DWORD NUM DUP(0)
count DWORD 0
sum DWORD 0
tempIndex DWORD 0

.code
main proc
	
	; --------------------------
	; ESI is used to store memory address
	; of current element in quadArray
	; --------------------------
	mov esi, OFFSET quadArray
	
	; --------------------------
	; Add first 4 base case values
	; into quadArray (1, 1, 1, 1)
	; --------------------------
	mov ecx, 4
	L1:
		mov DWORD PTR [esi], 1
		add esi, 4
		loop L1

	; --------------------------
	; calculate outer loop count
	; since we have first 4 values,
	; we iterate NUM - 4 times to fill in
	; values 5 through NUM
	; 
	; since we first jump to inner loop
	; only need to subtract 3 (pretest loop)
	; --------------------------
	mov count, NUM
	sub count, 3
	
	; --------------------------
	; setup start position for
	; calculating sum (back 4 DWORDs from ESI)
	; --------------------------
	sub esi, 16

	; --------------------------
	; start inside inner loop
	; --------------------------
	mov ecx, 4
	jmp L3

	; --------------------------
	; Loop through NUM - 4 times
	; --------------------------
	L2:
		; store calculated sum
		; from inner loop in quadArray
		mov eax, sum
		mov [esi], eax

		; move on to next value to calculate
		add esi, 4

		; reset sum
		mov sum, 0

		; save outer loop count
		mov count, ecx

		; set inner loop count
		mov ecx, 4
		
		; --------------------------
		; setup start position for
		; calculating sum (back 4 from ESI)
		; --------------------------
		sub esi, 16

		L3:
			; fetch sum
			mov eax, sum

			; add value at tempIndex to sum
			add eax, [esi]

			; store sum
			mov sum, eax
			
			; move on to next value
			add esi, 4

			loop L3

			; restore outer loop count
			mov ecx, count

			loop L2

	invoke ExitProcess,0
main endp
end main
