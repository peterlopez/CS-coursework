.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword


A_grade = 90 ;the minimum score to get an A (can be changed according to the grading scale used)
B_grade = 80 ;the minimum score to get a B (can be changed according to the grading scale used)
C_grade = 70 ;the minimum score to get a C (can be changed according to the grading scale used)
D_grade = 60 ;the minimum score to get a D (can be changed according to the grading scale used)

.data
letter_grade BYTE ? ; stores the letter grade ('A', 'B', 'C', 'D', or 'F')


.code

main proc
	mov eax, 4				; sample grade value
	call calculate_grade	; assign letter_grade

  invoke ExitProcess, 0
main endp


;
; This procedure stores the appropriate
; character in letter_grade based on the value in EAX
; and the constants A_grade, B_grade, C_grade, and D_grade
;
; Must setup EAX before calling this procedure
;
calculate_grade proc
	cmp eax, A_grade	; compare eax with highest grade
	jge assign_A		; jump to code label to assign 'A'
	
	cmp eax, B_grade	; then next highest...
	jge assign_B		; jump to code label to assign 'B'

	cmp eax, C_grade
	jge assign_C		; jump to code label to assign 'C'

	cmp eax, C_grade
	jge assign_D		; jump to code label to assign 'D'

	mov letter_grade, 'F'; lowest grade assigned
	ret					; return immediately do not fall down to code below

	assign_A:
		mov letter_grade, 'A'
		ret			; return immediately do not fall down to code below
	assign_B:
		mov letter_grade, 'B'
		ret			; return immediately do not fall down to code below
	assign_C:
		mov letter_grade, 'C'
		ret			; return immediately do not fall down to code below
	assign_D:
		mov letter_grade, 'D'
		ret

calculate_grade endp


end main
