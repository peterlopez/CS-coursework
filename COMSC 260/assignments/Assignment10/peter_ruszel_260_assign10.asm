; Program template

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

; this can be changed to any value between 2 - 16
; the correct V pattern should be "drawn" in memory in all cases
ROWS = 13

COLS = 2*ROWS-1
MID_COL = COLS/2

SPACE = 20h
STAR = 2ah

.data
; the memory allocated for the V pattern
V BYTE 32*ROWS dup (?)

; this refers to the current row in the pattern being processed
curr_row BYTE 0

; count is used to refer to a row in memory, such as 0x00404000 (row 0), 0x00404020 (row 32), 0x00404040 (row 64), etc
; You need to use count to move between the different rows in memory
; HINT: think about incrementing or decrementing count by 32
; you can change the initial value depending on if you want it to count up or down
count DWORD 0

l1_count DWORD 0

.code
main proc

	; -----------------------
	; Loop over top (n-1) rows
	; -----------------------
	mov ecx, ROWS - 1
	L1:
		; save L1 count
		mov l1_count, ecx

		; point ESI to start of current row
		mov esi, OFFSET V
		add esi, count
		
		; -----------------------
		; print spaces
		; before first asterisk
		;
		; use pre-test for first row case (no spaces)
		; -----------------------
		movzx ecx, curr_row
		inc ecx
		jmp EL2
		L2:
			mov BYTE PTR [esi], SPACE
			inc esi
			
			EL2:
				loop L2
		
	
		; print first asterisk
		mov BYTE PTR [esi], STAR


		; -----------------------
		; print spaces
		; between first and second asterisk
		; # of spaces:
		;	= COLS - (2 * curr_row) - 1
		;
		; use pre-test for final row case (no spaces)
		; -----------------------
		movzx eax, curr_row
		add eax, eax		; double curr_row
		
		mov ecx, COLS
		sub ecx, eax
		sub ecx, 1
		jmp EL3
		L3:
			inc esi
			mov BYTE PTR [esi], SPACE

			EL3:
				loop L3
	

		; print second asterisk at end of row
		inc esi
		mov BYTE PTR[esi], STAR

		; restore L1 count
		mov ecx, l1_count

		inc curr_row
		add count, 32

		loop L1


	; point ESI to start of last row
	mov esi, OFFSET V
	add esi, count


	; -----------------------
	; print spaces before
	; asterisk in last row
	; -----------------------
	mov ecx, MID_COL
	L4:
		mov BYTE PTR [esi], SPACE
		inc esi
	
		loop L4

	
	; print final asterisk
	mov BYTE PTR[esi], STAR

	invoke ExitProcess,0
main endp
end main