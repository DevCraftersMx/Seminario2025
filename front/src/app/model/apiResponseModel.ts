export interface ApiResponse<T> {
  data: T;
  exception: string | null;
  invalidParams: any | null;
  message: string;
  success: boolean;
  total: number;
}