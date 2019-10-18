.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
   array1 DWORD 5, 5, 5, 5, 5
   array2 DWORD 10, 10, 10, 10, 10
   array3 DWORD LENGTHOF array1 dup(?)

.code
main proc

	; Phase 1
	; reverse order and swap items
	; between array1 & array2 by using
	; exchange operation with direct-offset operands

	; Swap 1st element in array1
	mov eax, array1
	xchg [array2+16], eax
	mov array1, eax

	; Swap second element in array1
	mov eax, [array1+4]
	xchg [array2+12], eax
	mov [array1+4], eax

	; Swap thrid element in array1
	mov eax, [array1+8]
	xchg [array2+8], eax
	mov [array1+8], eax

	; Swap fourth element in array1
	mov eax, [array1+12]
	xchg [array2+4], eax
	mov [array1+12], eax

	; Swap fifth element in array1
	mov eax, [array1+16]
	xchg array2, eax
	mov [array1+16], eax

	

	; Phase 2
	; build array3 based on
	; subtration of array2 from array1

	; Build first element of array3
	mov eax, array1
	sub eax, array2
	mov array3, eax
	
	; Build second element of array3
	mov eax, [array1+4]
	sub eax, [array2+4]
	mov [array3+4], eax
	
	; Build third element of array3
	mov eax, [array1+8]
	sub eax, [array2+8]
	mov [array3+8], eax

	
	; Build fourth element of array3
	mov eax, [array1+12]
	sub eax, [array2+12]
	mov [array3+12], eax

	
	; Build fifth element of array3
	mov eax, [array1+16]
	sub eax, [array2+16]
	mov [array3+16], eax


	invoke ExitProcess,0
main endp
end main