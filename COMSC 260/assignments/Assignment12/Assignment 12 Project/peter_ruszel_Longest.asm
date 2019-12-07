; LongestIncreasingSequence      (Longest.asm)

; Assembly language subroutine that receives two input parameters: the offset 
; of an array and the array's size. It must return a count of the longest 
; strictly increasing sequence of integer values. 

.586
.model flat,C

LongestIncreasingSequence PROTO,
	arrayPtr:PTR DWORD, count:DWORD


MAX_INT = 2147483647

.code
;-----------------------------------------------
LongestIncreasingSequence PROC uses ecx esi edi,
  arrayPtr:PTR DWORD, count:DWORD
;
;-----------------------------------------------
	
	; ---------------------------------
	; EAX holds longest sequence which
	; will be returned after program finishes
	mov eax, 0
	; EDX holds the current running sequence
	; which, if higher than EAX, will be moved into EAX
	mov edx, 0

	; ---------------------------------
	; ESI is used for indirectly accessing array
	mov esi, arrayPtr
	; array index
	mov edi, 0

	; ---------------------------------
	; Setup loop counter
	mov ecx, count
	; subtract 1 to prevent accessing outside of array
	; since loop looks ahead at next element
	dec ecx

	L1:
		; EBX holds value of current element in array 
		; which will be compared to the next element
		mov ebx, [esi + edi*4]

		; move index to next element
		inc edi

		; compare current against next element
		; reset running total if next < current
		cmp [esi + edi*4], ebx
		jle reset

		; add to running total
		inc edx

		jmp skipReset

	reset:
		; compare running total to highest total
		cmp eax, edx
		jae skipReplace

		; replace EAX since EDX is bigger
		mov eax, edx

	skipReplace:
		mov edx, 0

	skipReset:
		loop L1
	
	ret
LongestIncreasingSequence ENDP
END
