; Program template

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

DAYS = 7     ; constant representing the number of days in a week
HOURS = 24   ; constant representing the number of hours in a day
MINUTES = 60  ; constant representing the number of minutes in an hour
SECONDS = 60  ; constant representing the number of seconds in a minute

.data

; byte 0000 stores the DAY
; byte 0001 stores the HOUR
; byte 0002 stores the MINUTE
; byte 0003 stores the SECOND
date_time DWORD ?

; counts the total number of iterations
num_of_iters DWORD 0 

; used to restore cl to the current day value
day BYTE DAYS

; used to restore cl to the current hour value
hour BYTE HOURS

; used to restore cl to the current minute value
minute BYTE MINUTES

.code
main proc
	mov ecx, SECONDS	; setup L4 counter
	jmp L4				; start from inner loop (seconds)

	; ---------------------
	; iterate through days
	L1:
		mov esi, OFFSET date_time + 1	; get addr of hour byte within date_time
		mov BYTE PTR [esi], 0			; reset hours

		dec day
		mov esi, OFFSET date_time		; get addr of hour byte within date_time
		inc BYTE PTR [esi]				; increment days

		mov day, cl						; save L1 counter
		mov cl, HOURS   				; setup L2 counter
		
	; ---------------------
	; iterate through hours
	L2:
		mov esi, OFFSET date_time + 2	; get addr of minute byte within date_time
		mov BYTE PTR [esi], 0			; reset minutes

		dec hour
		mov esi, OFFSET date_time + 1	; get addr of hour byte within date_time
		inc BYTE PTR [esi]				; increment hours

		mov hour, cl					; save L2 counter
		mov cl, MINUTES					; setup L3 counter

	; ---------------------
	; iterate through minutes
	L3:
		mov esi, OFFSET date_time + 3	; get addr of "second" byte within date_time
		mov BYTE PTR [esi], 0			; reset seconds

		dec minute
		mov esi, OFFSET date_time + 2	; get addr of minute byte within date_time
		inc BYTE PTR [esi]				; increment minutes

		mov minute, cl					; save L3 counter
		mov cl, SECONDS					; setup L4 counter
		
	; ---------------------
	; iterate through seconds
	L4:
		inc num_of_iters
		mov esi, OFFSET date_time + 3	; get addr of "second" byte within date_time
		inc BYTE PTR [esi]				; increment seconds
		loop L4

		mov cl, minute					; restore L3 counter
		loop L3

		mov cl, hour					; restore L2 counter
		loop L2

		mov cl, day						; restore L1 counter
		loop L1
		
		; Make correction for being off by 1 hour, 1 minute, and 1 second
		mov esi, OFFSET date_time + 3	; get addr of "second" byte within date_time
		dec BYTE PTR [esi]				; decrement seconds
		mov esi, OFFSET date_time + 2	; get addr of minute byte within date_time
		dec BYTE PTR [esi]				; decrement seconds
		mov esi, OFFSET date_time + 1	; get addr of hour byte within date_time
		dec BYTE PTR [esi]				; decrement seconds

	invoke ExitProcess,0
main endp
end main
